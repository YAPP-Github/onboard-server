package com.yapp.bol.terms

enum class TermsCode(
    val category: TermsCategory,
    val version: Int,
    val path: String,
) {
    SERVICE_V1(TermsCategory.SERVICE, 1, "terms.html"),
    PRIVACY_V1(TermsCategory.PRIVACY, 1, "privacy.html"),
}
