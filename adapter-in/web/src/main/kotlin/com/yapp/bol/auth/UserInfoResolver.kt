package com.yapp.bol.auth

import com.yapp.bol.auth.security.TokenAuthentication
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class UserInfoResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.parameterType == UserInfo::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): UserInfo? {
        val authentication = SecurityContextHolder.getContext()?.authentication as? TokenAuthentication ?: return null
        return UserInfo(authentication.principal)
    }
}

data class UserInfo(
    val id: Long,
)
