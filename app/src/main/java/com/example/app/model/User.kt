package com.example.app.model

import javax.persistence.*

@Entity
@Table(name = "Users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    var id: Int? = null

    @Column(name = "username", unique = true)
    var username: String? = null

    @Column(name = "password")
    var password: String? = null

    @Column(name = "enabled")
    var isEnabled = false
}