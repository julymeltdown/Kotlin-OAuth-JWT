package com.test.kakaologinoauth.controller

import com.test.kakaologinoauth.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authService: AuthService
) {
    @PostMapping("/auth/kakao/")
    fun kakaoLogin(

    ) : ResponseEntity<String> {



        return ResponseEntity.ok("kakao login")
    }
}