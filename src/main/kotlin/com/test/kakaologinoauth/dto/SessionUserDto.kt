package com.test.kakaologinoauth.dto

import com.test.kakaologinoauth.entity.User
import java.io.Serializable

data class SessionUserDto(
    var name: String? = null,
    var email: String? = null,
    var picture: String? = null,
) : Serializable{
    constructor(user: User) : this(
        name = user.name,
        email = user.email,
        picture = user.picture,
    )
}