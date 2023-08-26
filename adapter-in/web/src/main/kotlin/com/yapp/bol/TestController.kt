package com.yapp.bol

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO: Delete 테스트 코드 전용 클래스
@RestController
@RequestMapping("/v1/test")
class TestController(private val environment: Environment) {
    @GetMapping
    fun testGet(): TestResponse {
        val activeProfiles = environment.activeProfiles.joinToString(", ")

        return TestResponse("Good! You're running in the $activeProfiles phase.")
    }

    data class TestResponse(
        val value: String
    )
}
