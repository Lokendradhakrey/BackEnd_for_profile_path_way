package com.lokendra.BackEnd_for_profile_path_way.controllers;

import com.lokendra.BackEnd_for_profile_path_way.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/profile-path-way/v1/img")
public class FileController {
    @Autowired
    private FileService fileService;

    @Value("${project.file}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file) throws IOException {
        String uploadedFileName = this.fileService.uploadFile(path, file);
        return ResponseEntity.ok("File uploaded successfully! " + uploadedFileName);
    }

    @GetMapping("/{fileName}")
    public void getResourceFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        InputStream resourceFile = this.fileService.getResourceFile(path, fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourceFile, response.getOutputStream());
    }
}