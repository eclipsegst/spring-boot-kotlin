package com.example.app.service

import com.example.app.dao.AuthorityDao
import com.example.app.model.User
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service(value = "authService")
class AuthService(
    private val userService: UserService,
    private val tokenStore: TokenStore,
    private val authorityDao: AuthorityDao
) : UserDetailsService {
    private val TAG = "AuthService"

    override fun loadUserByUsername(username: String): UserDetails {
        val user =
            userService.findByUsername(username) ?: throw UsernameNotFoundException(username)
        val grantedAuths: MutableList<SimpleGrantedAuthority> = ArrayList()

        /*
         * If there's no Authority, we will get error like,
         * {
         *   "error": "access_denied",
         *   "error_description": "Access is denied"
         * }
         */
        val authorities = authorityDao.findByUsername(username)
        for (authority in authorities) {
            grantedAuths.add(SimpleGrantedAuthority(authority.authority))
        }
        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            grantedAuths
        )
    }

    fun signIn(usernameOrEmail: String?, password: String?): String {
        // Find username, if not found, throw "Could not find for X" error
        // Validate password
        return ""
    }

    fun signUp(user: User) {
        userService.insert(user)
    }

    fun signOut(tokenValue: String?) {
        // Reference: https://stackoverflow.com/a/32320860
        val accessToken = tokenStore.readAccessToken(tokenValue)
        tokenStore.removeAccessToken(accessToken)
    }
}