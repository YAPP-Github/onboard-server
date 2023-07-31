package com.yapp.bol.file

import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<FileEntity, Long> {
    fun findAllByPurpose(purpose: FilePurpose): List<FileEntity>
}
