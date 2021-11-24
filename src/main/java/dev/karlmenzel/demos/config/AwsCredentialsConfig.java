package dev.karlmenzel.demos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.auth.StsAssumeRoleCredentialsProvider;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.regions.Region;

@Configuration
public class AwsCredentialsConfig {
    @Value("${dev.karlmenzel.roleArn}")
    private String roleArn;
    
    @Bean
    public StsAssumeRoleCredentialsProvider credentialsProviderBean() {
        System.out.println("AWS_ACCESS_KEY_ID: " + System.getenv("AWS_ACCESS_KEY_ID"));
        System.out.println("AWS_SECRET_ACCESS_KEY: " + System.getenv("AWS_SECRET_ACCESS_KEY"));

        StsClient stsClient = StsClient.builder()
        .region(Region.US_EAST_1)
        .build();

        AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
            .roleSessionName("karlTestSessionName")
            .roleArn(roleArn)
            .build();

        StsAssumeRoleCredentialsProvider credentialsProvider = StsAssumeRoleCredentialsProvider.builder()
            .refreshRequest(roleRequest)
            .stsClient(stsClient)
            .asyncCredentialUpdateEnabled(true)
            .build();

        return credentialsProvider;
    }
}
