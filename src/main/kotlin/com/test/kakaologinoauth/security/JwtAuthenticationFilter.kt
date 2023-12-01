package com.test.kakaologinoauth.security

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) : GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, filterChain: FilterChain) {
        val claims = jwtTokenProvider.resolveToken(request as HttpServletRequest)
        if (claims != null) {
            SecurityContextHolder.getContext().authentication =
                jwtTokenProvider.getAuthentication(claims)
        }
        filterChain.doFilter(request, response)
    }
}