package com.yapp.bol.base

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import com.yapp.bol.ExceptionHandler
import com.yapp.bol.auth.UserId
import com.yapp.bol.auth.security.TokenAuthentication
import io.kotest.core.spec.style.FunSpec
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.mock.web.MockPart
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.headers.ResponseHeadersSnippet
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet
import org.springframework.restdocs.request.PathParametersSnippet
import org.springframework.restdocs.request.QueryParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.restdocs.request.RequestDocumentation.queryParameters
import org.springframework.restdocs.snippet.Snippet
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

abstract class ControllerTest : FunSpec() {
    protected abstract val controller: Any

    private val restDocumentation = ManualRestDocumentation()
    private lateinit var mockMvc: MockMvc

    init {
        beforeSpec {
            setUpMockMvc()
        }
        beforeEach {
            restDocumentation.beforeTest(javaClass, it.name.testName)
        }
        afterEach {
            restDocumentation.afterTest()
            SecurityContextHolder.clearContext()
        }
    }

    private fun setUpMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .apply<StandaloneMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
                    .uris().withScheme("http").withHost("localhost").and()
                    .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .setControllerAdvice(ExceptionHandler())
//            .setCustomArgumentResolvers()
//            .setMessageConverters()
            .build()
    }

    protected fun get(url: String, buildRequest: MockHttpServletRequestBuilder.() -> Unit): ResultActions =
        mockMvc.perform(get(url).apply(buildRequest))

    protected fun get(
        url: String,
        vararg pathParams: String,
        buildRequest: MockHttpServletRequestBuilder.() -> Unit
    ): ResultActions =
        mockMvc.perform(get(url, pathParams).apply(buildRequest))

    protected fun post(url: String, buildRequest: MockHttpServletRequestBuilder.() -> Unit): ResultActions =
        mockMvc.perform(post(url).apply(buildRequest))

    protected fun post(
        url: String,
        request: Any,
        buildRequest: MockHttpServletRequestBuilder.() -> Unit
    ): ResultActions =
        mockMvc.perform(
            post(url).apply {
                contentType(MediaType.APPLICATION_JSON)
                content(objectMapper.writeValueAsString(request))
            }.apply(buildRequest)
        )

    protected fun multipart(
        url: String,
        mockFiles: List<MockMultipartFile>,
        mockParts: List<MockPart>,
        buildRequest: MockHttpServletRequestBuilder.() -> Unit
    ): ResultActions {
        return mockMvc.perform(
            RestDocumentationRequestBuilders.multipart(url)
                .apply {
                    mockFiles.forEach { this.file(it) }
                    mockParts.forEach { this.part(it) }
                }
                .apply(buildRequest)
        )
    }

    protected fun delete(url: String, buildRequest: MockHttpServletRequestBuilder.() -> Unit): ResultActions =
        mockMvc.perform(delete(url).apply(buildRequest))

    protected fun patch(url: String, buildRequest: MockHttpServletRequestBuilder.() -> Unit): ResultActions =
        mockMvc.perform(patch(url).apply(buildRequest))

    protected fun put(url: String, buildRequest: MockHttpServletRequestBuilder.() -> Unit): ResultActions =
        mockMvc.perform(put(url).apply(buildRequest))

    protected fun MockHttpServletRequestBuilder.authorizationHeader(userId: UserId) {
        SecurityContextHolder.getContext().authentication = TokenAuthentication("Token", userId)

        this.header("Authorization", "Bearer Token")
    }

    protected fun ResultActions.isStatus(code: Int): ResultActions =
        andExpect(status().`is`(code))

    protected fun ResultActions.makeDocument(
        documentInfo: DocumentInfo,
        vararg snippets: Snippet
    ): ResultActions =
        andDo(
            document(
                identifier = documentInfo.identifier,
                resourceDetails = ResourceSnippetParametersBuilder()
                    .description(documentInfo.description)
                    .deprecated(documentInfo.deprecated)
                    .tag(documentInfo.tag.value),
                snippets = snippets
            )
        )

    protected infix fun String.type(fieldType: DocumentFieldType): DocumentField {
        return DocumentField(this, fieldType.type)
    }

    protected infix fun <T : Enum<T>> String.type(fieldType: ENUM<T>): DocumentField {
        return DocumentField(this, fieldType.type, fieldType.enums)
    }

    protected infix fun String.means(description: String): DocumentField =
        DocumentField(this).apply {
            means(description)
        }

    protected fun requestHeaders(vararg fields: DocumentField): RequestHeadersSnippet =
        HeaderDocumentation.requestHeaders(
            fields.map(DocumentField::toHeaderDescriptor).toList()
        )

    protected fun responseHeaders(vararg fields: DocumentField): ResponseHeadersSnippet =
        HeaderDocumentation.responseHeaders(
            fields.map(DocumentField::toHeaderDescriptor).toList()
        )

    protected fun pathParameters(vararg fields: DocumentField): PathParametersSnippet =
        pathParameters(
            fields.map(DocumentField::toParameterDescriptor).toList()
        )

    protected fun queryParameters(vararg fields: DocumentField): QueryParametersSnippet =
        queryParameters(
            fields.map(DocumentField::toParameterDescriptor).toList()
        )

    protected fun requestFields(vararg fields: DocumentField): RequestFieldsSnippet =
        requestFields(
            fields.map(DocumentField::toFieldDescriptor).toList()
        )

    protected fun responseFields(vararg fields: DocumentField): ResponseFieldsSnippet =
        responseFields(
            fields.map(DocumentField::toFieldDescriptor).toList()
        )

    protected data class DocumentInfo(
        val identifier: String,
        val tag: OpenApiTag,
        val description: String? = null,
        val deprecated: Boolean = false,
    )

    companion object {
        @JvmStatic
        protected val objectMapper = ObjectMapper()
    }
}
