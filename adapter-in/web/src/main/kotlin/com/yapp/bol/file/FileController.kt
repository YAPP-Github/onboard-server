package com.yapp.bol.file

import com.yapp.bol.InvalidRequestException
import com.yapp.bol.file.dto.RawFileData
import com.yapp.bol.file.dto.UploadFileResponse
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

        return UploadFileResponse(result.url)
    }
}
