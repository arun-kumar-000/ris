package com.live.ris.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/files")
public class FileController {

    private final Path templatesPath = Paths.get("reports");
    private final Path reportResultPath = Paths.get("results");


    @GetMapping("/list")
    public ResponseEntity<List<String>> listFiles() {
        try (Stream<Path> files = Files.walk(templatesPath, 1)) {
            List<String> fileList = files
                    .filter(path -> !Files.isDirectory(path))
                    .map(templatesPath::relativize)
                    .map(Path::toString)
                    .filter(name -> name.toLowerCase().endsWith(".doc") || name.toLowerCase().endsWith(".docx"))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(fileList);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        try {
            // Prevent path traversal attack
            Path resolvedPath = reportResultPath.resolve(filename).normalize();
            if (!resolvedPath.startsWith(reportResultPath)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

//            // If blank.docx, copy from classpath if not exists
//            if ("blank.docx".equalsIgnoreCase(filename)) {
//                Path blankPath = templatesPath.resolve("blank.docx");
//                if (!Files.exists(blankPath)) {
//                    Files.createDirectories(templatesPath);
//                    Resource template = new ClassPathResource("templates/blank.docx");
//                    try (InputStream in = template.getInputStream()) {
//                        Files.copy(in, blankPath, StandardCopyOption.REPLACE_EXISTING);
//                    }
//                }
//            }

            Resource resource = new UrlResource(resolvedPath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/save-callback")
    public ResponseEntity<Map<String, Object>> handleSaveCallback(@RequestBody Map<String, Object> body) {
        try {
            int status = (int) body.getOrDefault("status", -1);
            System.out.println("📝 Received callback with status: " + status);

            // Only save if changes are saved or Ctrl+S was triggered
            if (status == 2 || status == 3 || status == 6) {
                String fileUrl = (String) body.get("url");
                String key = (String) body.get("key");

                // Decode the file name safely from base64
                String fileName = new String(Base64.getDecoder().decode(key), StandardCharsets.UTF_8);
                Path savePath = reportResultPath.resolve(fileName).normalize();

                // Prevent directory traversal
                if (!savePath.startsWith(reportResultPath)) {
                    System.err.println("🚫 Invalid save path attempt!");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }

                // Download and save the updated file
                try (InputStream in = new URL(fileUrl).openStream()) {
                    Files.copy(in, savePath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("✅ File saved successfully: " + savePath);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    System.err.println("❌ Error saving file.");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                         .body(Map.of("error", 1));
                }
            } else {
                System.out.println("ℹ️ Status does not require saving (status=" + status + ")");
            }

            // Respond with success
            return ResponseEntity.ok(Map.of("error", 0));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", 1));
        }
    }

}
