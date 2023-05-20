package com.yapp.bol.social.naver

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class NaverConfiguration {
    @Bean
    internal fun naverOpenApiClient(): NaverOpenApiClient {
        val webClient = WebClient.builder().baseUrl("https://openapi.naver.com").build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
        return factory.createClient(NaverOpenApiClient::class.java)
    }
}
