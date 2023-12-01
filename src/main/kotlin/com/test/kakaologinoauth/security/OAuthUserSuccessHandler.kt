package com.test.kakaologinoauth.security

import com.test.kakaologinoauth.dto.OAuthAttributes
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

class OAuthUserSuccessHandler(
    private val jwtTokenProvider: JwtTokenProvider,
): SimpleUrlAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(request: HttpServletRequest?, response: HttpServletResponse?, authentication: org.springframework.security.core.Authentication?) {
        val oAuthAttributes: OAuthAttributes = authentication?.principal as OAuthAttributes
        val email = oAuthAttributes.email
        // 권한 정보 생성

    }
    private val redirectStrategy = DefaultRedirectStrategy()

    private fun redirect(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        url: String
    ) {
        redirectStrategy.sendRedirect(request, response, url)
    }
}