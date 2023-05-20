package com.yapp.bol.base

import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.snippet.Attributes

class DocumentField(
    private val name: String,
    private var fieldType: JsonFieldType? = null,
    private val enumValues: List<Any>? = null
) {
    private var description: String? = null
    private var isOptimal = false

    infix fun means(description: String): DocumentField {
        this.description = description
        return this
    }

    infix fun isOptional(value: Boolean): DocumentField {
        this.isOptimal = value
        return this
    }

    fun toFieldDescriptor(): FieldDescriptor =
        PayloadDocumentation.fieldWithPath(name).also {
            it.type(fieldType)
            if (description != null) it.description(description)
            if (isOptimal) it.optional()
            if (enumValues != null) {
                it.description("${description ?: ""} oneOf $enumValues")
                // 아래 방식은 지원은 한다고 적혀있는데 당장 안되는 것 같다... https://github.com/ePages-de/restdocs-api-spec/issues/146
                it.attributes(Attributes.Attribute("enumValues", enumValues))
            }
        }

    fun toHeaderDescriptor(): HeaderDescriptor {
        return HeaderDocumentation.headerWithName(name).also {
            if (description != null) it.description(description)
            if (isOptimal) it.optional()
        }
    }

    fun toParameterDescriptor(): ParameterDescriptor {
        return parameterWithName(name).also {
            if (description != null) it.description(description)
            if (isOptimal) it.optional()
        }
    }
}
