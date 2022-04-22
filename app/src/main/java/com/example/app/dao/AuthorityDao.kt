package com.example.app.dao

import com.example.app.model.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

/**
 * Reference:
 * JpaRepository: https://stackoverflow.com/a/14025100
 */
@Repository
interface AuthorityDao : JpaRepository<Authority?, String?>, JpaSpecificationExecutor<Authority?> {
    fun findByUsername(username: String?): List<Authority>
}