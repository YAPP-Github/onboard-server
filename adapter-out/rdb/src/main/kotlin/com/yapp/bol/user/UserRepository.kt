package com.yapp.bol.user

import org.springframework.data.jpa.repository.JpaRepository

internal interface UserRepository : JpaRepository<UserEntity, Long>
