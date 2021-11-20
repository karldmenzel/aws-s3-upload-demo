package dev.karlmenzel.demos.processors;

import java.io.File;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.sts.auth.StsAssumeRoleCredentialsProvider;

@Component
public class FileUploadProcessor {

    private final StsAssumeRoleCredentialsProvider credentialsProvider;

    @Autowired
    public FileUploadProcessor(StsAssumeRoleCredentialsProvider credentialsProvider) {
        this.credentialsProvider = credentialsProvider;
    }

    public boolean uploadFile(){
        S3Client s3Client = buildS3Client();

        File file = new File(URI.create("file:/Users/kmenzel/Desktop/HelloWorld.txt"));

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket("dev.karlmenzel.personalwebsite")
            .key("test-folder-2/HelloWorld.txt")
            .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));

        return true;
    }

    private S3Client buildS3Client() {
        return S3Client.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(credentialsProvider)
            .build();
    }
}
