package com.test.kakaologinoauth.entity

import jakarta.persistence.*


@Entity
class User(
    @field:Id
    val id: Long? = null,
    @field:Column(name = "email")
    var email: String,
    @field:Column(name = "name")
    var name: String,
    @field:Column(name = "picture")
    var picture: String,
    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "role")
    val role: Role
) : BaseTimeEntity() {
    constructor(
        email: String,
        name: String,
        picture: String,
    ) : this(
        email = email,
        name = name,
        picture = picture,
        role = Role.USER
    )

    fun update(name: String, picture: String) {
        this.name = name
        this.picture = picture
    }

    fun getRoleKey(): String {
        return this.role.key
    }
}