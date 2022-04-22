package com.example.app.service

import com.example.app.dao.AuthorityDao
import com.example.app.dao.UserDao
import com.example.app.model.Authority
import com.example.app.model.User
import org.springframework.lang.NonNull
import org.springframework.lang.Nullable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service(value = "userService")
class UserService(
    private val userDao: UserDao,
    private val authorityDao: AuthorityDao,
    var passwordEncoder: PasswordEncoder
) {
    private val TAG = "UserService"

    @Nullable
    fun insert(@NonNull user: User): User? {
        val userOptional = userDao.findByUsername(user.username)
        userOptional?.let {
            return null
        }

        user.password = passwordEncoder.encode(user.password)
        val authority = Authority()
        authority.username = user.username
        authority.authority = "ROLE_USER"
        authorityDao.save(authority)
        val newUser = userDao.save(user)
        return newUser
    }

    private fun checkIfUserExist(username: String): Boolean {
        return userDao.findByUsername(username) != null
    }

    @Nullable
    fun update(@NonNull user: User): User? {
        val userOptional = userDao.findByUsername(user.username)
        return userOptional?.let { update(it, user) }
    }

    private fun update(@Nullable old: User, newValue: User): User {
        return userDao.save(newValue)
    }

    fun findAll(): List<User?> {
        return ArrayList(userDao.findAll())
    }

    @Nullable
    fun findById(id: String): User? {
        return userDao.findById(id).orElse(null)
    }

    fun delete(id: String) {
        userDao.deleteById(id)
    }

    fun findByUsername(username: String?): User? {
        return userDao.findByUsername(username)
    }
}