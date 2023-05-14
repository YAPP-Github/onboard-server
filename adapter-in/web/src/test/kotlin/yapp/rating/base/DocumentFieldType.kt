package yapp.rating.base

import org.springframework.restdocs.payload.JsonFieldType

sealed class DocumentFieldType(
    val type: JsonFieldType
)

object ARRAY : DocumentFieldType(JsonFieldType.ARRAY)
object BOOLEAN : DocumentFieldType(JsonFieldType.BOOLEAN)
object OBJECT : DocumentFieldType(JsonFieldType.OBJECT)
object NUMBER : DocumentFieldType(JsonFieldType.NUMBER)
object NULL : DocumentFieldType(JsonFieldType.NULL)
object STRING : DocumentFieldType(JsonFieldType.STRING)
object ANY : DocumentFieldType(JsonFieldType.VARIES)
