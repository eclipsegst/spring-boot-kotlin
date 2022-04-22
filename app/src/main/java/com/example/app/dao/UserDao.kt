package com.example.app.dao

import com.example.app.model.User
import io.micrometer.core.lang.Nullable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

/**
 * Reference:
 * JpaRepository: https://stackoverflow.com/a/14025100
 */
@Repository
interface UserDao : JpaRepository<User?, String?>, JpaSpecificationExecutor<User?> {
    @Nullable
    fun findByUsername(username: String?): User?
}