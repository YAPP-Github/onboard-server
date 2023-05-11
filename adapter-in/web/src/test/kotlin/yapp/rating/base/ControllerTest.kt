package yapp.rating.base

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.FunSpec
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
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
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.restdocs.request.RequestDocumentation.queryParameters
import org.springframework.restdocs.snippet.Snippet
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
//            .setControllerAdvice(ExceptionHandler())
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

    protected fun delete(url: String, buildRequest: MockHttpServletRequestBuilder.() -> Unit): ResultActions =
        mockMvc.perform(delete(url).apply(buildRequest))

    protected fun patch(url: String, buildRequest: MockHttpServletRequestBuilder.() -> Unit): ResultActions =
        mockMvc.perform(patch(url).apply(buildRequest))

    protected fun put(url: String, buildRequest: MockHttpServletRequestBuilder.() -> Unit): ResultActions =
        mockMvc.perform(put(url).apply(buildRequest))

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
                    .tag(documentInfo.tag),
                snippets = snippets
            )
        )

    protected infix fun String.type(fieldType: DocumentFieldType): DocumentField {
        return DocumentField(this, fieldType.type)
    }

    protected infix fun String.means(description: String): DocumentField {
        return DocumentField(this).apply {
            means(description)
        }
    }

    protected fun requestHeaders(vararg fields: DocumentField) {
        HeaderDocumentation.requestHeaders(
            fields.map(DocumentField::toHeaderDescriptor).toList()
        )
    }

    protected fun responseHeaders(vararg fields: DocumentField) {
        HeaderDocumentation.responseHeaders(
            fields.map(DocumentField::toHeaderDescriptor).toList()
        )
    }

    protected fun pathParameters(vararg fields: DocumentField) {
        pathParameters(
            fields.map(DocumentField::toParameterDescriptor).toList()
        )
    }

    protected fun queryParameters(vararg fields: DocumentField) {
        queryParameters(
            fields.map(DocumentField::toParameterDescriptor).toList()
        )
    }

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
        val tag: String, // TODO: Enum 으로 변경 하면 좋을듯
        val description: String? = null,
        val deprecated: Boolean = false,
    )

    companion object {
        @JvmStatic
        protected val objectMapper = ObjectMapper()
    }
}
