package com.yapp.bol.jwt

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.yapp.bol.jwt.key.JwtKey
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import java.time.LocalDateTime
import java.util.*


object JwtUtils {
    val objectMapper = ObjectMapper() // inline function 때문에 public
    private val mapTypeReference = object : TypeReference<Map<String, String>>() {}

    fun generate(id: String, expiredAt: LocalDateTime, jwtKey: JwtKey): String =
        Jwts.builder()
            .setId(id)
            .setExpiration(expiredAt.toDate())
            .signWith(jwtKey.getKey())
            .compact()

    fun validate(token: String, jwtKey: JwtKey): Boolean =
        try {
            Jwts.parserBuilder()
                .setSigningKey(jwtKey.getKey())
                .build()
                .parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            false
        }

    fun getUserId(token: String, jwtKey: JwtKey): String =
        Jwts.parserBuilder()
            .setSigningKey(jwtKey.getKey())
            .build()
            .parseClaimsJws(token).body.id

    fun getHeader(token: String): Map<String, String> {
        val headerOfToken: String = token.substring(0, token.indexOf("."))
        val headerDecode = String(Base64.getDecoder().decode(headerOfToken), charset("UTF-8"))
        return objectMapper.readValue(headerDecode, mapTypeReference)
    }

    inline fun <reified T> getPayload(token: String): T {
        val payloadOfToken: String = token.substring(token.indexOf(".") + 1, token.lastIndexOf("."))
        val payloadDecode = String(Base64.getDecoder().decode(payloadOfToken), charset("UTF-8"))
        return objectMapper.readValue<T>(payloadDecode)
    }
}
