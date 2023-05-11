package yapp.rating.base

import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName

class DocumentField(
    private val name: String,
    private var fieldType: JsonFieldType? = null,
) {
    private var description: String? = null
        private set
    private var isOptimal = false
        private set

    infix fun means(description: String): DocumentField {
        this.description = description
        return this
    }

    infix fun isOptional(value: Boolean): DocumentField {
        this.isOptimal = value
        return this
    }

    fun toFieldDescriptor(): FieldDescriptor =
        PayloadDocumentation.fieldWithPath(name).apply {
            type(fieldType)
            if (description != null) description(description)
            if (isOptimal) optional()
        }

    fun toHeaderDescriptor(): HeaderDescriptor {
        return HeaderDocumentation.headerWithName(name).apply {
            if (description != null) description(description)
            if (isOptimal) optional()
        }
    }

    fun toParameterDescriptor(): ParameterDescriptor {
        return parameterWithName(name).apply {
            if (description != null) description(description)
            if (isOptimal) optional()
        }
    }
}
