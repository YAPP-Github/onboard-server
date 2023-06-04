package com.yapp.bol.file

import com.yapp.bol.InvalidRequestException
import com.yapp.bol.file.dto.RawFileData
import com.yapp.bol.file.dto.UploadFileResponse
import jakarta.websocket.server.PathParam
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/v1/file")
@RestController
class FileController(
    private val fileService: FileService,
) {
    @Value("\${bol.server.host}")
    private lateinit var hostUrl :String

    @PostMapping
    fun uploadFile(
        @RequestPart file: MultipartFile,
        @RequestParam purpose: FilePurpose,
    ): UploadFileResponse {
        val userId = 0L
        val request = RawFileData(
            userId = userId,
            contentType = file.contentType ?: throw InvalidRequestException,
            content = file.inputStream,
            purpose = purpose,
        )
        val result = fileService.uploadFile(request)

        return UploadFileResponse("$hostUrl/v1/file/${result.name}")
    }

    @GetMapping("/{name}")
    fun downloadFile(@PathParam("name") fileName: String){}
}
