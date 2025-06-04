package com.live.ris.services;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.live.ris.dto.TemplateFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DocServices {

    public String generateReportFromTemplate(String templatePath, String outputPath, Map<String, String> replacements) {
        try (FileInputStream fis = new FileInputStream(templatePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            // Replace text in paragraphs
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                replaceInParagraph(paragraph, replacements);
            }

            // Replace text in tables
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            replaceInParagraph(paragraph, replacements);
                        }
                    }
                }
            }

            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                document.write(fos);
            }

            return outputPath;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TemplateFile> getAllTemplates() {
        File folder = new File("reports");
        if (!folder.exists()) {
            folder.mkdirs(); // create if not exists
        }
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".doc") || name.endsWith(".docx"));
        List<TemplateFile> list = new ArrayList<>();
        for (File file : files) {
            list.add(new TemplateFile(file.getName(), Instant.ofEpochMilli(file.lastModified())));
        }
        return list;
    }

    public void saveTemplate(MultipartFile file) throws IOException {
        Path path = Paths.get("reports", file.getOriginalFilename());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
    }

    public void deleteTemplate(String name) {
        File file = new File("reports/" + name);
        if (file.exists()) file.delete();
    }

    
    private void replaceInParagraph(XWPFParagraph paragraph, Map<String, String> replacements) {
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null) {
                for (Map.Entry<String, String> entry : replacements.entrySet()) {
                    if (text.contains(entry.getKey())) {
                        text = text.replace(entry.getKey(), entry.getValue() != null ? entry.getValue() : "");
                    }
                }
                run.setText(text, 0);
            }
        }
    }
}
