package com.yapp.bol.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "bol.server")
data class BolProperties(
    val host: String
)
