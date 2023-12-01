package com.test.kakaologinoauth.service

import com.test.kakaologinoauth.dto.OAuthAttributes
import com.test.kakaologinoauth.dto.SessionUserDto
import com.test.kakaologinoauth.entity.User
import com.test.kakaologinoauth.repository.UserRepository
import jakarta.servlet.http.HttpSession
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    @Value("\${spring.security.oauth2.client.registration.kakao.client-id}") private val apiKey: String,
    @Value("\${spring.security.oauth2.client.registration.kakao.redirect-uri}") private val redirectUrl: String,
    private val userRepository: UserRepository,
    private val httpSession: HttpSession
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()
        val oAuthUser: OAuth2User = delegate.loadUser(userRequest)

        val registrationId: String = userRequest?.clientRegistration?.registrationId ?: ""
        val userNameAttributeName: String =
            userRequest?.clientRegistration?.providerDetails?.userInfoEndpoint?.userNameAttributeName ?: ""
        // 네이버 카카오 구분
        val oAuthAttributes = OAuthAttributes(
            attributes = oAuthUser.attributes,
            userNameAttributeName = userNameAttributeName,
            name = oAuthUser.attributes["name"].toString(),
            email = oAuthUser.attributes["email"].toString(),
            picture = oAuthUser.attributes["picture"].toString()
        )
        val attributes = oAuthAttributes.of(registrationId)
        val user: User = saveOrUpdate(attributes)
        httpSession.setAttribute("user", SessionUserDto(user))

        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority(user.getRoleKey())),
            attributes.attributes,
            attributes.attributes["name"].toString()
        )
    }

    private fun saveOrUpdate(attributes: OAuthAttributes): User {
        return userRepository.findByEmail(attributes.email)?.run {
            update(attributes.name, attributes.picture)
            userRepository.save(this)
        } ?: userRepository.save(
            User(
                email = attributes.email,
                name = attributes.name,
                picture = attributes.picture
            )
        )
    }

}