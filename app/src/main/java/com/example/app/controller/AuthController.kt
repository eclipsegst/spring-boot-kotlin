package com.example.app.controller

import com.example.app.model.User
import com.example.app.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
class AuthController(private val authService: AuthService) {
    fun signIn(usernameOrEmail: String?, password: String?) {
        authService.signIn("", "")
    }

    @RequestMapping(value = ["/auth/signup"], method = [RequestMethod.POST])
    fun signUp(@RequestBody user: User) {
        authService.signUp(user)
    }

    @RequestMapping(path = ["/auth/signout"], method = [RequestMethod.DELETE])
    @ResponseStatus(HttpStatus.OK)
    fun signOut(request: HttpServletRequest) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader != null) {
            val tokenValue = authHeader.replace("Bearer", "").trim { it <= ' ' }
            authService.signOut(tokenValue)
        }
    }
}