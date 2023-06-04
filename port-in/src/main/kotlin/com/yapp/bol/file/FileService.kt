package com.yapp.bol.file

import com.yapp.bol.file.dto.RawFileData

interface FileService {
    fun uploadFile(request: RawFileData): FileInfo
    fun downloadFile(userId: Long?, fileName: String): RawFileData
}
