package com.example.app.controller

import com.example.app.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    operator fun get(principal: Principal): ResponseEntity<Principal> {
        return ResponseEntity.ok(principal)
    }
}