package dev.karlmenzel.demos.processors;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.sts.auth.StsAssumeRoleCredentialsProvider;

@Component
public class FileUploadProcessor {

    private final StsAssumeRoleCredentialsProvider credentialsProvider;
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileUploadProcessor(StsAssumeRoleCredentialsProvider credentialsProvider, ResourceLoader resourceLoader) {
        this.credentialsProvider = credentialsProvider;
        this.resourceLoader = resourceLoader;
    }

    public boolean uploadFile(){

        // Resource downloadedFile = resourceLoader.getResource("s3://dev.karlmenzel.personalwebsite/test-folder/HelloWorld.txt");

        // WritableResource writableResource = (WritableResource) downloadedFile;
        // try (OutputStream outputStream = writableResource.getOutputStream()) {
        //     outputStream.write("test".getBytes());
        // } catch (IOException e) {
        //     throw new RuntimeException("Uh oh, could not write!");
        // }

        // if(downloadedFile.exists()) {
        //     System.out.println("File exists!");

        //     try {
        //         Scanner input = new Scanner(downloadedFile.getFile());

        //         while (input.hasNextLine())
        //         {
        //             System.out.println(input.nextLine());
        //         }
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }

        // } else {
        //     throw new RuntimeException("Uh oh, file not found!");
        // }

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
