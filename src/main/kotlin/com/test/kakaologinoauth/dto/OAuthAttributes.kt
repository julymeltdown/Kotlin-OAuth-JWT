package com.test.kakaologinoauth.dto


data class OAuthAttributes(
    var attributes: Map<String, Any>,
    var userNameAttributeName: String,
    var name: String,
    var email: String,
    var picture: String
) {
    fun of(
        registrationId: String
    ): OAuthAttributes {
        return if ("naver" == registrationId) {
            ofNaver("id", this.attributes)
        } else if ("google" == registrationId) {
            ofGoogle(userNameAttributeName, this.attributes)
        } else { // kakao
            ofKakao("id", this.attributes)
        }
    }

    private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
        return OAuthAttributes(
            attributes,
            userNameAttributeName,
            attributes["name"].toString(),
            attributes["email"].toString(),
            attributes["picture"].toString()
        )
    }

    private fun ofNaver(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
        return OAuthAttributes(
            attributes,
            userNameAttributeName,
            attributes["name"].toString(),
            attributes["email"].toString(),
            attributes["picture"].toString()
        )
    }

    private fun ofKakao(
        userNameAttributeName: String,
        attributes: Map<String, Any>
    ): OAuthAttributes {
        return OAuthAttributes(
            attributes,
            userNameAttributeName,
            attributes["name"].toString(),
            attributes["email"].toString(),
            attributes["picture"].toString()
        )
    }
}