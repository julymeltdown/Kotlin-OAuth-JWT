package com.test.kakaologinoauth.config

import com.test.kakaologinoauth.service.AuthService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig(
    private val customOauth2UserService: AuthService
) {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            // convert .headers().frameOptions().disable() to new syntax
            .headers { it -> it.frameOptions { it.disable() } }
            .cors { }
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
            .oauth2Login { it ->
                it.userInfoEndpoint {
                    it.userService(customOauth2UserService)
                }
            }
        return http.build()
    }
}