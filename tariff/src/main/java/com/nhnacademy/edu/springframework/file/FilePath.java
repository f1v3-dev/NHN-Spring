package com.nhnacademy.edu.springframework.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FilePath {

    private final String filePath;

    public FilePath(@Value("${file.path}") String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
