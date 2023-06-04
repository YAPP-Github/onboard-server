package com.yapp.bol.file

import com.yapp.bol.base.ControllerTest
import com.yapp.bol.base.OpenApiTag
import com.yapp.bol.base.STRING
import com.yapp.bol.config.BolProperties
import io.mockk.every
import io.mockk.mockk
import java.io.InputStream
import org.springframework.mock.web.MockMultipartFile
import org.springframework.mock.web.MockPart
import org.springframework.restdocs.payload.PayloadDocumentation.requestPartBody
import org.springframework.restdocs.request.RequestDocumentation.partWithName
import org.springframework.restdocs.request.RequestDocumentation.requestParts

class FileControllerTest : ControllerTest() {
    private val fileService: FileService = mockk()
    private val properties = BolProperties("http://localhost:8080")
    override val controller = FileController(fileService, properties)

    init {
        test("File Upload") {
            val userId = 0L
            val inputStream: InputStream = "data".byteInputStream()
            val contentType = "image/jpeg"
            val purpose = FilePurpose.GROUP_IMAGE

            val mockFile = MockMultipartFile("file", "file-name", contentType, inputStream)
            val mockPart = MockPart("purpose", purpose.toString().toByteArray())

            every { fileService.uploadFile(any()) } returns FileInfo("URL", "image/jpeg")

            multipart("/v1/file", listOf(mockFile), listOf(mockPart)) {}
                .isStatus(200)
                .makeDocument(
                    DocumentInfo(
                        identifier = "file",
                        description = "https://www.notion.so/yapp-workspace/API-Doc-23c98b2c35964072a8a5920f88f776b0?pvs=4 (multipart 문서 어캐 쓰는지 모르겠네요 ㅜㅜ)",
                        tag = OpenApiTag.FILE
                    ),
                    requestHeaders(
                        "Content-Type" type STRING means "multipart/form-data 고정",
                    ),
                    requestPartBody("purpose"),
                    requestParts(
                        partWithName("file").description("올리고자 하는 파일"),
                        partWithName("purpose").description("올리는 파일의 목적").optional(),
                    ),
                    responseFields(
                        "url" type STRING means "다운로드 할 수 있는 URL",
                    )
                )
        }
    }
}
