package com.yapp.bol.social.apple

data class AppleIdTokenPayload(
    val iss: String, // "https://appleid.apple.com",
    val aud: String, // "onboard.onboard-iOS",
//    val exp: Long, // 1695973961,
//    val iat: Long, // 1695887561,
    val sub: String, // "000027.1a77badf74084337b940813589cb842b.0628",
//    val cHash: String, // "1E5f-xAfjnVOrHurJEbrGw",
//    val email: String, // "dtnx6krrqk@privaterelay.appleid.com",
//    val emailVerified: String, // "true",
//    val isPrivateEmail: String, // "true",
//    val authTime: Long, // 1695887561,
//    val nonceSupported: Boolean, // true,
)
