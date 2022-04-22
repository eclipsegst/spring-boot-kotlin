package com.example.app.model

import javax.persistence.IdClass
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "authorities")
@IdClass(AuthorityKey::class)
class Authority {
    @Id
    var username: String? = null

    @Id
    var authority: String? = null
}

internal class AuthorityKey : Serializable {
    private val username: String? = null
    private val authority: String? = null
}