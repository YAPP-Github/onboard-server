package com.yapp.bol.user

import com.yapp.bol.UnknownException
import com.yapp.bol.auth.getSecurityUserIdOrThrow
import com.yapp.bol.user.dto.MyInfoResponse
import com.yapp.bol.user.dto.toResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/user")
@RestController
class UserController(
    private val userService: UserService,
) {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    fun getMe(): MyInfoResponse {
        val userId = getSecurityUserIdOrThrow()
        val user = userService.getUser(userId) ?: throw UnknownException

        return user.toResponse()
    }
}
