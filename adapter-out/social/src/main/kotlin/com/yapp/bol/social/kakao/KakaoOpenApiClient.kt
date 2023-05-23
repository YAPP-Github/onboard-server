package com.yapp.bol.social.kakao

import com.yapp.bol.social.kakao.dto.KakaoUserResponse
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.service.annotation.GetExchange
import reactor.core.publisher.Mono

internal interface KakaoOpenApiClient {
    @GetExchange("/v2/user/me")
    fun getTokenInfo(
        @RequestHeader("Authorization") authorization: String,
    ): Mono<KakaoUserResponse>
}
