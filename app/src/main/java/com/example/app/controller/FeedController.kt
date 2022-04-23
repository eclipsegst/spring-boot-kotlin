package com.example.app.controller

import com.example.app.model.Feed
import com.example.app.service.FeedService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
class FeedController(private val feedService: FeedService) {
    private val tag = javaClass.toString()

    @PostMapping("/me/feed")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun createFeed(@RequestBody feed: Feed): ResponseEntity<Feed> {
        val result = feedService.save(feed)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/me/feed")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getFeedsByCreatorId(@RequestParam userId: String): ResponseEntity<List<Feed>> {
        val feeds = feedService.findFeedCreatedBy(userId)
        return ResponseEntity.ok(feeds)
    }
}
