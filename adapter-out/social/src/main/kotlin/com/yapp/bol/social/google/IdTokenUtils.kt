package com.yapp.bol.social.google

import com.fasterxml.jackson.databind.ObjectMapper
import java.util.Base64
import org.springframework.stereotype.Component

@Component
internal class IdTokenUtils(
    private val objectMapper: ObjectMapper
) {

    fun getKid(token: String): String? {
        val jwtParts = token.split("\\.".toRegex())

        val header = String(Base64.getDecoder().decode(jwtParts[0]))
        val headerNode = objectMapper.readTree(header)

        return headerNode["kid"]?.textValue()
    }

    fun getSub(token: String): String? {
        val jwtParts = token.split("\\.".toRegex())

        val payload = String(Base64.getDecoder().decode(jwtParts[1]))
        val payloadNode = objectMapper.readTree(payload)

        return payloadNode["sub"]?.textValue()
    }
}
