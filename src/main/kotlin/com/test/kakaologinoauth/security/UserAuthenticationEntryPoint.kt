package com.test.kakaologinoauth.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class UserAuthenticationEntryPoint: AuthenticationEntryPoint {
    @Override
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse?, authException: org.springframework.security.core.AuthenticationException?) {
        response?.sendError(401, "Unauthorized")
    }
}