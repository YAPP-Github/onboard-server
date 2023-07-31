package com.yapp.bol.aws

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cloud.aws")
data class AwsProperties(
    val region: String,
    val credentials: AwsCredentialsProperties,
    val s3: S3Properties,
)

data class AwsCredentialsProperties(
    val accessKey: String,
    val secretKey: String,
)

data class S3Properties(
    val bucket: String,
)
