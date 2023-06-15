package com.yapp.bol.file

import com.yapp.bol.InvalidRequestException
import com.yapp.bol.auth.getSecurityUserId
import com.yapp.bol.auth.getSecurityUserIdOrThrow
import com.yapp.bol.file.dto.RawFileData
import com.yapp.bol.file.dto.UploadFileResponse
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
    @PreAuthorize("isAuthenticated()")
    fun uploadFile(
        @RequestPart file: MultipartFile,
        @RequestParam purpose: FilePurpose,
    ): UploadFileResponse {
        val request = RawFileData(
            userId = getSecurityUserIdOrThrow(),
            contentType = file.contentType ?: throw InvalidRequestException,
            content = file.inputStream,
            purpose = purpose,
        )
        val result = fileService.uploadFile(request)

        return UploadFileResponse(FileNameConverter.convertFileUrl(result.name))
    }

    @GetMapping("/{name}")
    fun downloadFile(@PathVariable("name") fileName: String): ResponseEntity<Resource> {
        val file = fileService.downloadFile(getSecurityUserId(), fileName)
        val resource = InputStreamResource(file.content)

        return ResponseEntity.ok()
            .contentType(MediaType.valueOf(file.contentType))
            .body(resource)
    }
}
