package com.test.kakaologinoauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class KakaoLoginOauthApplication

fun main(args: Array<String>) {
    runApplication<KakaoLoginOauthApplication>(*args)
}
