package com.test.kakaologinoauth.security

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class UserAccessDeniedHandler: AccessDeniedHandler {
    @Override
    override fun handle(request: HttpServletRequest?, response: HttpServletResponse?, accessDeniedException: org.springframework.security.access.AccessDeniedException?) {
        response?.sendError(403, "Access Denied")
    }
}