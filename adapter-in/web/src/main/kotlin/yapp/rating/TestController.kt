package yapp.rating

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO: Delete 테스트 코드 전용 클래스
@RestController
@RequestMapping("/v1/test")
class TestController {

    @GetMapping
    fun testGet(): TestResponse {
        return TestResponse("Good!")
    }

    data class TestResponse(
        val value: String
    )
}
