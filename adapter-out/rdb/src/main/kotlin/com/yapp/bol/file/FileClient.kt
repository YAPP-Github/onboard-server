package com.yapp.bol.file

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.yapp.bol.aws.AwsProperties
import com.yapp.bol.file.dto.RawFileData
import java.util.UUID
import org.springframework.stereotype.Component

@Component
class FileClient(
    private val fileRepository: FileRepository,
    private val s3Client: AmazonS3,
    private val awsProperties: AwsProperties,
) : FileCommandRepository {
    override fun saveFile(file: RawFileData): FileInfo {
        val key = UUID.randomUUID().toString()
        val accessLevel = file.purpose.accessLevel

        val metadata = ObjectMetadata().apply {
            contentType = file.contentType
            addUserMetadata("access_level", accessLevel.toString())
            addUserMetadata("userId", file.userId.toString())
        }

        s3Client.putObject(awsProperties.s3.bucket, key, file.content, metadata)

        val entity = FileEntity(key, file.userId, accessLevel)
        fileRepository.save(entity)

        return FileInfo(key, file.contentType)
    }
}
