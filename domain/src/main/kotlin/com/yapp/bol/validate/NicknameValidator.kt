package com.yapp.bol.validate

import com.yapp.bol.InvalidNicknameException

object NicknameValidator {
    private const val MIN_NICKNAME_LENGTH = 1
    private const val MAX_NICKNAME_LENGTH = 10

    fun validate(nickname: String) {
        if (nickname.length < MIN_NICKNAME_LENGTH || nickname.length > MAX_NICKNAME_LENGTH) throw InvalidNicknameException
    }
}
