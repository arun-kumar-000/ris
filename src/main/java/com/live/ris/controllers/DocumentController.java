package com.live.ris.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.util.Map;

@RestController
public class DocumentController {

    private static final String SAVE_DIR = "reports/";

    @PostMapping("/save-callback")
    public ResponseEntity<Map<String, Object>> handleSaveCallback(@RequestBody Map<String, Object> data) {
        int status = (int) data.get("status");

        // Status 2: document is ready for saving
        if (status == 2 || status == 6) {
            try {
                String url = (String) data.get("url");
                String fileName = "sample.docx"; // You may pass this dynamically using 'key'
                try (InputStream in = new URL(url).openStream()) {
                    Files.copy(in, Paths.get(SAVE_DIR + fileName), StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.ok(Map.of("error", 1));
            }
        }
        return ResponseEntity.ok(Map.of("error", 0));
    }
}
