package com.yapp.bol.auth

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
    ): SecurityFilterChain {
        http
            .cors().and().csrf().disable()
            .exceptionHandling()
//            .authenticationEntryPoint(authenticationEntryPoint)
//            .accessDeniedHandler(accessDeniedHandler)
            .and()
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
            "/v1/auth/**"
        )
    }
}
