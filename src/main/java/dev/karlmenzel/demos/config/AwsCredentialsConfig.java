package dev.karlmenzel.demos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import software.amazon.awssdk.services.sts.StsClient;
import software.amazon.awssdk.services.sts.auth.StsAssumeRoleCredentialsProvider;
import software.amazon.awssdk.services.sts.model.AssumeRoleRequest;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.STSAssumeRoleSessionCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;

@Configuration
public class AwsCredentialsConfig {
    @Value("${dev.karlmenzel.roleArn}")
    private String roleArn;
    
    // @Bean
    // @Primary
    // public AwsCredentialsProvider credentialsProviderBean() {
    //     System.out.println("AWS_ACCESS_KEY_ID: " + System.getenv("AWS_ACCESS_KEY_ID"));
    //     System.out.println("AWS_SECRET_ACCESS_KEY: " + System.getenv("AWS_SECRET_ACCESS_KEY"));

    //     StsClient stsClient = StsClient.builder()
    //     .region(Region.US_EAST_1)
    //     .build();
    

    //     AssumeRoleRequest roleRequest = AssumeRoleRequest.builder()
    //         .roleSessionName("karlTestSessionName")
    //         .roleArn(roleArn)
    //         .build();

    //     StsAssumeRoleCredentialsProvider credentialsProvider = StsAssumeRoleCredentialsProvider.builder()
    //         .refreshRequest(roleRequest)
    //         .stsClient(stsClient)
    //         .asyncCredentialUpdateEnabled(true)
    //         .build();

    //     return credentialsProvider;
    // }

    @Bean
    @Primary
    public AWSCredentialsProvider awsCredentialsProvider() {
        // AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
        //         .withClientConfiguration(clientConfiguration())
        //         .withCredentials(awsCredentialsProvider)
        //         .build();

        AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("sts-endpoint.amazonaws.com", "us-east-1"))
                .build();

        return new STSAssumeRoleSessionCredentialsProvider
                .Builder(roleArn, "test")
                .withStsClient(stsClient)
                .build();
    }
}
