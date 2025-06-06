package com.live.ris.controllers;

import com.live.ris.services.DocServices;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/templates")
public class TemplateController {

	private final Path templatesPath = Paths.get("reports");

	@Autowired
	private DocServices docServices;

	// Show list of templates
	@GetMapping
	public String showTemplateList(Model model) {
		model.addAttribute("templates", docServices.getAllTemplates());
		return "template_list";
	}

	// Upload new template
	@PostMapping("/upload")
	public String uploadTemplate(@RequestParam("file") MultipartFile file) throws IOException {
		docServices.saveTemplate(file);
		return "redirect:/templates";
	}

	// Delete template by filename
	@GetMapping("/delete/{name}")
	public String deleteTemplate(@PathVariable String name) {
		docServices.deleteTemplate(name);
		return "redirect:/templates";
	}

	@GetMapping("/edit/{name}")
	public String openEditorPage(@PathVariable String name, Model model) {
		model.addAttribute("fileName", name);
		return "editor"; // Loads editor.html via Thymeleaf
	}


	// Show template creation page (optional)
	@GetMapping("/new/{name}")
	public String newEditorPage(@PathVariable String name, Model model) {
		try {
			Path sourceFile = Paths.get("src/main/resources/static/blank.docx");
			Path templatesDir = Paths.get("reports"); // adjust if your folder is different
			Files.createDirectories(templatesDir);
			Path targetFile = templatesDir.resolve(name).normalize();
			if (!targetFile.startsWith(templatesDir)) {
				return "redirect:/templates";
			}
			if (!Files.exists(targetFile)) {
				Files.copy(sourceFile, targetFile);
				System.out.println("✅ File created: " + targetFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "redirect:/templates?error=creation_failed";
		}
		model.addAttribute("fileName", name);
		return "editor"; 
	}

	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		try {
			// Prevent path traversal attack
			Path resolvedPath = templatesPath.resolve(filename).normalize();
			if (!resolvedPath.startsWith(templatesPath)) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
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
				Path savePath = templatesPath.resolve(fileName).normalize();

				// Prevent directory traversal
				if (!savePath.startsWith(templatesPath)) {
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
