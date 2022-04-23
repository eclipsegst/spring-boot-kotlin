package com.example.app.dao

import com.example.app.model.Feed
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * Further improvement: implement pagination by using PagingAndSortingRepository
 * Reference: https://www.baeldung.com/spring-data-jpa-pagination-sorting
 */
@Repository
interface FeedDao : JpaRepository<Feed?, String?>, JpaSpecificationExecutor<Feed?> {
    /**
     * Find all feeds for a given user id
     */
    @Query(value = "SELECT * FROM feeds m WHERE m.creator_id = ?1 order by created_at desc", nativeQuery = true)
    fun findByCreatorIdOrderByCreatedAtDesc(@Param("userId") userId: String): List<Feed>
}
