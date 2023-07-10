package com.yapp.bol.terms

enum class TermsCategory(val title: String, val isOptional: Boolean) {
    SERVICE("서비스 이용약관", false),
    PRIVACY("개인정보 처리방침", false),
}
