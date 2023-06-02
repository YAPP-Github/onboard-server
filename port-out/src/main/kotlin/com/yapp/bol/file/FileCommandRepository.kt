package com.yapp.bol.file

import com.yapp.bol.file.dto.RawFileData

interface FileCommandRepository {
    fun saveFile(file: RawFileData): FileInfo
}
