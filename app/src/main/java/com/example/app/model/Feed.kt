package com.example.app.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "feeds")
class Feed(
    @Id
    var id: String = UUID.randomUUID().toString(),
    var text: String = "",
    var creatorId: String = "",
    var createdAt: Date = Date()
)
