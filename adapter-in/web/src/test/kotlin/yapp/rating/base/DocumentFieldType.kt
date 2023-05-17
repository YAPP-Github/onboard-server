package yapp.rating.base

import org.springframework.restdocs.payload.JsonFieldType
import kotlin.reflect.KClass

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

data class ENUM<T : Enum<T>>(val enums: List<T>) : DocumentFieldType(JsonFieldType.STRING) {
    constructor(clazz: KClass<T>) : this(clazz.java.enumConstants.asList()) // (1)
}
