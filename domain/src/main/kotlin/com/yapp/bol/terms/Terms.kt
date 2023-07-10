package com.yapp.bol.terms

data class Terms(
    val code: TermsCode,
    val url: String,
) {
    val title = code.category.title
}
