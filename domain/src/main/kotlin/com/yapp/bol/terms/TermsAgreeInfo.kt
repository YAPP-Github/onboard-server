package com.yapp.bol.terms

data class TermsAgreeInfo(
    val termsCode: TermsCode,
    val isAgree: Boolean,
)

operator fun List<TermsAgreeInfo>.get(termsCode: TermsCode): TermsAgreeInfo? {
    return find { it.termsCode == termsCode }
}

fun List<TermsAgreeInfo>.isAgreed(termsCode: TermsCode): Boolean {
    return get(termsCode)?.isAgree ?: false
}
