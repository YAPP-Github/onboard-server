package yapp.rating.auth

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import yapp.rating.auth.dto.LoginRequest
import yapp.rating.auth.dto.LoginResponse
import yapp.rating.auth.dto.toResponse

@RestController
@RequestMapping("/v1/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): LoginResponse =
        authService.login(request.type, request.token).toResponse()
}
