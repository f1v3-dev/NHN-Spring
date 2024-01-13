package com.nhnacademy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.nio.file.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class FileControllerTest {

    private String UPLOAD_DIR = "/Users/seungjo/Desktop/NHN-Spring/NHN-Mart/src/main/webapp/WEB-INF/img/";

    MockMvc mockMvc;
    FileController fileController;

    @BeforeEach
    void setUp() {
        fileController = new FileController();
        mockMvc = MockMvcBuilders.standaloneSetup(fileController).build();
    }

    @Test
    @DisplayName("downloadFile - 성공")
    void downloadFile_Success() throws Exception {

        File file = new File(UPLOAD_DIR + "test.jpg");
        Files.createFile(file.toPath());

        ReflectionTestUtils.setField(fileController, "UPLOAD_DIR", UPLOAD_DIR);

        mockMvc.perform(get("/file/download/{fileName}", "test.jpg"))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.CONTENT_DISPOSITION))
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.jpg"))
                .andExpect(content().contentType(MediaType.APPLICATION_OCTET_STREAM));

        Files.delete(file.toPath());
    }

    @Test
    @DisplayName("downloadFile - 실패")
    void downloadFile_File() throws Exception {
        mockMvc.perform(get("/file/download/{fileName}", "notFound.jpg"))
                .andExpect(status().isNotFound());
    }
}