package com.yapp.bol.social.google

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("social.google")
data class GoogleApiProperties(
    val clientId: String,
)
