package com.yapp.bol.file

import com.yapp.bol.NotFoundFileException
import com.yapp.bol.file.dto.RawFileData
import org.springframework.stereotype.Service

@Service
class FileServiceImpl(
    private val fileQueryRepository: FileQueryRepository,
    private val fileCommandRepository: FileCommandRepository,
) : FileService {
    override fun uploadFile(request: RawFileData): FileInfo {
        return fileCommandRepository.saveFile(request)
    }

    override fun downloadFile(userId: Long, fileName: String): RawFileData {
        val fileData = fileQueryRepository.getFile(fileName) ?: throw NotFoundFileException

        if (fileData.canAccess(userId).not()) throw NotFoundFileException

        return fileData
    }

    private fun RawFileData.canAccess(userId: Long): Boolean =
        when (this.purpose.accessLevel) {
            FileAccessLevel.PUBLIC -> true
            FileAccessLevel.PRIVATE -> this.userId == userId
        }
}
