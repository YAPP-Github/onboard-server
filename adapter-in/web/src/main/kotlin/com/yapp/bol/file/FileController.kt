package com.yapp.bol.file

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/v1/file")
@RestController
class FileController(
    private val fileService: FileService,
)
