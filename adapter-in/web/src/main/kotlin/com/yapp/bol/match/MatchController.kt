package com.yapp.bol.match

import com.yapp.bol.EmptyResponse
import com.yapp.bol.match.dto.CreateMatchRequest
import com.yapp.bol.match.dto.toDto
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/match")
class MatchController(
    private val matchService: MatchService,
) {
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    fun createMatch(@RequestBody request: CreateMatchRequest): EmptyResponse {
        matchService.createMatch(request.toDto())

        return EmptyResponse
    }
}
