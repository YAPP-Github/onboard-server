package com.yapp.bol.auth.security

import com.yapp.bol.UnAuthenticationException
import com.yapp.bol.UnAuthorizationException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver

@Component
class SecurityExceptionHandler(
    @Qualifier("handlerExceptionResolver") private val handler: HandlerExceptionResolver,
) : AuthenticationEntryPoint, AccessDeniedHandler {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        handler.resolveException(request, response, null, UnAuthenticationException(authException))
    }

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        handler.resolveException(request, response, null, UnAuthorizationException(accessDeniedException))
    }
}
