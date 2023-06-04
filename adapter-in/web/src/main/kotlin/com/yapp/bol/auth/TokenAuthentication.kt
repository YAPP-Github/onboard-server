package com.yapp.bol.auth

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class TokenAuthentication(
    private val token: String,
    private val userId: Long,
) : Authentication {
    private var isAuthenticated = true

    override fun getName(): String = userId.toString()

    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()

    override fun getCredentials(): String = token

    override fun getDetails(): Any? = null

    override fun getPrincipal(): Long = userId

    override fun isAuthenticated(): Boolean = isAuthenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }
}
