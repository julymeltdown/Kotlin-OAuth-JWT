package com.test.kakaologinoauth.repository

import com.test.kakaologinoauth.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}