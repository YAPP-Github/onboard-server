package com.yapp.bol.file

import com.yapp.bol.file.dto.RawFileData
import org.springframework.stereotype.Service

@Service
class FileServiceImpl(
    private val fileCommandRepository: FileCommandRepository
) : FileService {
    override fun uploadFile(request: RawFileData): FileInfo {
        return fileCommandRepository.saveFile(request)
    }
}
