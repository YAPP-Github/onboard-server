package com.yapp.bol.social.naver

import com.yapp.bol.social.naver.dto.NaverUserResponse
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange
import reactor.core.publisher.Mono

internal interface NaverOpenApiClient {
    @GetExchange("/v1/nid/me")
    fun getUserProfile(
        @RequestHeader("Authorization") authorization: String,
    ): Mono<NaverUserResponse>
}
