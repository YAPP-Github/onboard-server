package com.yapp.bol.file

import com.yapp.bol.file.dto.UploadFileRequest

interface FileService {
    fun uploadFile(request: UploadFileRequest): FileInfo
}
