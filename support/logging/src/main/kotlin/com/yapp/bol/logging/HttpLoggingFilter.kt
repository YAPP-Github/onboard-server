package com.yapp.bol.logging

import com.yapp.bol.utils.logger
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse

class HttpLoggingFilter : Filter {
    private val logger = logger()

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        chain.doFilter(request, response)
    }
}
