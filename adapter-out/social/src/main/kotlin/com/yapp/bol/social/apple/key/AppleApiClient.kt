package com.yapp.bol.social.apple.key

import org.springframework.web.service.annotation.GetExchange
import reactor.core.publisher.Mono

interface AppleApiClient {
    @GetExchange("https://appleid.apple.com/auth/keys")
    fun getPublicKeys(): Mono<ApplePublicKeysResponse>
}
