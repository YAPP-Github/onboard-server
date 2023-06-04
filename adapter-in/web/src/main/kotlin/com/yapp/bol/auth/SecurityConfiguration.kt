package com.yapp.bol.auth

import com.yapp.bol.auth.security.SecurityExceptionHandler
import com.yapp.bol.auth.security.TokenAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfiguration {

    @Bean
    fun securityFilter(
        http: HttpSecurity,
        tokenAuthenticationFilter: TokenAuthenticationFilter,
        securityExceptionHandler: SecurityExceptionHandler,
    ): SecurityFilterChain {
        http
            .cors().and().csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(securityExceptionHandler)
            .accessDeniedHandler(securityExceptionHandler)
            .and()
            .anonymous().disable()
            .authorizeHttpRequests {
                it.requestMatchers(*WHITE_LIST).permitAll()
                it.anyRequest().authenticated()
            }
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .formLogin().disable().headers().frameOptions().disable()
        return http.build()
    }

    companion object {
        private val WHITE_LIST = arrayOf(
            "/v1/auth/**",
            "/swagger/**",
        )
    }
}
