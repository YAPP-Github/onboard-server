package com.yapp.bol.file.dto

import com.yapp.bol.auth.UserId
import com.yapp.bol.file.FilePurpose
import java.io.InputStream

data class RawFileData(
    val userId: UserId,
    val contentType: String,
    val content: InputStream,
    val purpose: FilePurpose,
)
