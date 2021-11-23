package dev.karlmenzel.demos.processors;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.amazonaws.auth.AWSCredentialsProvider;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.sts.auth.StsAssumeRoleCredentialsProvider;

@Component
public class FileUploadProcessor {

    // private final AwsCredentialsProvider credentialsProvider;
    // private final ResourceLoader resourceLoader;

    // @Autowired
    // public FileUploadProcessor(AwsCredentialsProvider credentialsProvider, ResourceLoader resourceLoader) {
    //     this.credentialsProvider = credentialsProvider;
    //     this.resourceLoader = resourceLoader;
    // }

    private final AWSCredentialsProvider credentialsProvider;
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileUploadProcessor(AWSCredentialsProvider credentialsProvider, ResourceLoader resourceLoader) {
        this.credentialsProvider = credentialsProvider;
        this.resourceLoader = resourceLoader;
    }

    public boolean appendToFile() {
        System.out.println("getting aws file");
        Resource resource = resourceLoader.getResource("s3://dev.karlmenzel.personalwebsite/test-folder/HelloWorld.txt");

        System.out.println("got aws file");
        File downloadedS3Object = new File(resource.getFilename());
     
        System.out.println("copying aws file");
        try (InputStream inputStream = resource.getInputStream()) {
            Files.copy(inputStream, downloadedS3Object.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("uh oh, failed to copy the file:\n" + e.getMessage());
        }

        return true;
    }

    // public boolean uploadFile(){
    //     S3Client s3Client = buildS3Client();

    //     File file = new File(URI.create("file:/Users/kmenzel/Desktop/HelloWorld.txt"));

    //     PutObjectRequest putObjectRequest = PutObjectRequest.builder()
    //         .bucket("dev.karlmenzel.personalwebsite")
    //         .key("test-folder/HelloWorld.txt")
    //         .build();

    //     s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));

    //     return true;
    // }

    // private S3Client buildS3Client() {
    //     return S3Client.builder()
    //         .region(Region.US_EAST_1)
    //         .credentialsProvider(credentialsProvider)
    //         .build();
    // }
}
