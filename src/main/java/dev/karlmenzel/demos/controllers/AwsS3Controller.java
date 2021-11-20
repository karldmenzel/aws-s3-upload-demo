package dev.karlmenzel.demos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.karlmenzel.demos.processors.FileUploadProcessor;


@RestController
public class AwsS3Controller {

    private final FileUploadProcessor fileUploadProcessor;

    @Autowired
    public AwsS3Controller(FileUploadProcessor fileUploadProcessor) {
        this.fileUploadProcessor = fileUploadProcessor;
    }

    @CrossOrigin(origins = {"http://localhost", "http://localhost:3000"})
    @GetMapping("/uploadFile")
    public String uploadFile() {
        fileUploadProcessor.uploadFile();

        return "Hello world";
    }
}