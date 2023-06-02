package com.yapp.bol.aws

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(AwsProperties::class)
class AwsConfiguration {

    @Bean
    fun s3Client(
        properties: AwsProperties,
    ): AmazonS3 {
        val credential = BasicAWSCredentials(properties.credentials.accessKey, properties.credentials.secretKey)

        return AmazonS3ClientBuilder.standard()
            .withRegion(properties.region)
            .withCredentials(AWSStaticCredentialsProvider(credential))
            .build()
    }
}
