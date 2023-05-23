package com.yapp.bol.social.kakao

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class KakaoConfiguration {
    @Bean
    internal fun kakaoOpenApiClient(): KakaoOpenApiClient {
        val webClient = WebClient.builder().baseUrl(KAKAO_OPEN_API_URL).build()
        val factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient)).build()
        
        return factory.createClient(KakaoOpenApiClient::class.java)
    }

    companion object {
        private const val KAKAO_OPEN_API_URL = "https://kapi.kakao.com"
    }
}
