package com.yapp.bol.validate

object NicknameValidator {
    private const val MIN_NICKNAME_LENGTH = 1
    private const val MAX_NICKNAME_LENGTH = 10
    private val regex = Regex("^[a-zA-Z0-9_가-힣 ]*$") // 한글, 영어, 숫자, 언더 스코어, 스페이스

    fun validate(nickname: String): Boolean {
        return validateLength(nickname) && validateRegex(nickname)
    }

    private fun validateRegex(nickname: String): Boolean {
        return regex.matches(nickname)
    }

    private fun validateLength(nickname: String): Boolean {
        return nickname.length in MIN_NICKNAME_LENGTH..MAX_NICKNAME_LENGTH && nickname.isNotBlank()
    }
}
