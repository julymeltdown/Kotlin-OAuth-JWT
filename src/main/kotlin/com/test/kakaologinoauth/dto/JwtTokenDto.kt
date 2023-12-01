package com.test.kakaologinoauth.dto

data class JwtTokenDto(
    val token: String,
    val expiredIn: Long
)