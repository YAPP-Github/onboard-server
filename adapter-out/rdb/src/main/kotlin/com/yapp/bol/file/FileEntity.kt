package com.yapp.bol.file

import com.yapp.bol.AuditingEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "file")
@Entity
class FileEntity(
    name: String,
    userId: Long,
    purpose: FilePurpose,
) : AuditingEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id", nullable = false)
    var id: Long = 0
        protected set

    @Column(name = "name")
    var name: String = name
        protected set

    @Column(name = "users_id")
    var userId: Long = userId
        protected set

    @Column(name = "purpose")
    @Enumerated(value = EnumType.STRING)
    var purpose: FilePurpose = purpose
        protected set
}
