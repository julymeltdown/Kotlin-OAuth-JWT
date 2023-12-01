package com.test.kakaologinoauth.controller

import com.test.kakaologinoauth.dto.SessionUserDto
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthTemplateController(
    private val httpSession: HttpSession
) {
    @GetMapping("/")
    fun index(
        model: Model
    ): String {
        val user: SessionUserDto = httpSession!!.getAttribute("user") as SessionUserDto
        if (user != null) {
            model.addAttribute("userName", user.name)
        }
        return "index"
    }
}