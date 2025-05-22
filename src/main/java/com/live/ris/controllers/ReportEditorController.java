package com.live.ris.controllers;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ReportEditorController {

    private static final String TEMPLATE_DIR = "src/main/resources/templates/reports";

    // 1. Load HTML page with list of templates (.doc & .docx)
    @GetMapping("/report-editor")
    public String loadEditorPage(Model model) throws IOException {
        List<String> docFiles = Files.list(Paths.get(TEMPLATE_DIR))
                .filter(path -> path.toString().endsWith(".docx") || path.toString().endsWith(".doc"))
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());

        model.addAttribute("templates", docFiles);
        return "report-editor";
    }

    // 2. Load selected .doc or .docx content
    @GetMapping("/getDocx/{fileName}")
    @ResponseBody
    public ResponseEntity<String> getDocFileContent(@PathVariable String fileName) {
        Path path = Paths.get(TEMPLATE_DIR, fileName);
        if (!Files.exists(path)) return ResponseEntity.notFound().build();

        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            StringBuilder content = new StringBuilder();

            if (fileName.endsWith(".docx")) {
                XWPFDocument docx = new XWPFDocument(fis);
                for (XWPFParagraph para : docx.getParagraphs()) {
                    content.append(para.getText()).append("\n");
                }
            } else if (fileName.endsWith(".doc")) {
                HWPFDocument doc = new HWPFDocument(fis);
                Range range = doc.getRange();
                content.append(range.text());
            } else {
                return ResponseEntity.badRequest().body("Unsupported file type.");
            }

            return ResponseEntity.ok(content.toString());

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error reading file");
        }
    }

    // 3. Save edited content back to .doc or .docx file
    @PostMapping("/saveDocx")
    @ResponseBody
    public ResponseEntity<String> saveDocFile(@RequestBody Map<String, String> payload) {
        String fileName = payload.get("fileName");
        String content = payload.get("content");

        if (fileName == null || (!fileName.endsWith(".docx") && !fileName.endsWith(".doc"))) {
            return ResponseEntity.badRequest().body("Unsupported file type");
        }

        Path path = Paths.get(TEMPLATE_DIR, fileName);

        try (FileOutputStream fos = new FileOutputStream(path.toFile())) {

            if (fileName.endsWith(".docx")) {
                XWPFDocument doc = new XWPFDocument();
                for (String line : content.split("\n")) {
                    XWPFParagraph para = doc.createParagraph();
                    para.createRun().setText(line);
                }
                doc.write(fos);

            } else if (fileName.endsWith(".doc")) {
                Path templatePath = Paths.get(TEMPLATE_DIR, "blank.doc");
                if (!Files.exists(templatePath)) {
                    return ResponseEntity.internalServerError().body("blank.doc template not found");
                }

                try (FileInputStream templateInputStream = new FileInputStream(templatePath.toFile())) {
                    HWPFDocument doc = new HWPFDocument(templateInputStream);
                    Range range = doc.getRange();
                    range.delete(); // Clear existing content
                    range.insertAfter(content);
                    doc.write(fos);
                }
            
            } else {
                return ResponseEntity.badRequest().body("Unsupported file type");
            }

            return ResponseEntity.ok("Saved");

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to save file");
        }
    }
}
