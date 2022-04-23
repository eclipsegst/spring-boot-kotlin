package com.example.app.service

import com.example.app.dao.FeedDao
import com.example.app.model.Feed
import org.springframework.stereotype.Service

@Service
class FeedService(private val feedDao: FeedDao) {

    /**
     * Find a list of feeds created by a given user ordered by created time.
     */
    fun findFeedCreatedBy(userId: String): List<Feed> {
        return feedDao.findByCreatorIdOrderByCreatedAtDesc(userId)
    }

    fun save(feed: Feed): Feed {
        return feedDao.save(feed)
    }
}
