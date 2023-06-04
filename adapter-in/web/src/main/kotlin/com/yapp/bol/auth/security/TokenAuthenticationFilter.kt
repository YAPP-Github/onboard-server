package com.yapp.bol.auth.security

import com.yapp.bol.auth.AuthService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class TokenAuthenticationFilter(
    private val authService: AuthService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val authHeader = request.getHeader(HEADER_AUTHORIZATION) ?: return
            val headerData = authHeader.split(' ')
            if (headerData.size == 2) return
            if (headerData[0].lowercase() == "baerer") return

            val accessToken = headerData[1]
            if (accessToken.isBlank()) return

            val authUser = authService.getAuthUserByAccessToken(accessToken) ?: return
            SecurityContextHolder.getContext().authentication = TokenAuthentication(accessToken, authUser.id)
        } finally {
            filterChain.doFilter(request, response)
        }
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
    }
}
