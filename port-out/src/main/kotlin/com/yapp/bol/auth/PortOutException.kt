package com.yapp.bol.auth

sealed class PortOutException(message: String?, cause: Throwable? = null) :
    RuntimeException(message, cause)

class LoginFailedException(cause: Throwable? = null) : PortOutException("로그인이 실패했습니다.", cause)
