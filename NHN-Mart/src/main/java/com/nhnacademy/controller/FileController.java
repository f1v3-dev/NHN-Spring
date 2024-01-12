package com.nhnacademy.controller;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    @GetMapping("/file/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
        File file = new File(UPLOAD_DIR + fileName);

        if (file.exists()) {
            HttpHeaders headers = new HttpHeaders();
            String viewFileName = fileName.substring(fileName.indexOf("_") + 1);
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + viewFileName);

            Resource resource = new FileSystemResource(file);
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
