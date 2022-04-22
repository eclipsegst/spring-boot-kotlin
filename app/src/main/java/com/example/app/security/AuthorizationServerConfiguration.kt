package com.example.app.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import javax.sql.DataSource

/**
 * Reference: https://github.com/marcosbarbero/spring-boot2-oauth2-jwt/tree/master/oauth2-jwt-server
 *
 * Schema
 * spring-security-oauth2 schema.sql:
 * https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
 * https://gist.github.com/fernandomantoan/3ff4b90d7e9eae4a5d1e
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration(
    private val dataSource: DataSource,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
) : AuthorizationServerConfigurerAdapter() {

    @Bean
    fun tokenStore(): TokenStore {
        // It will save or retrieve access_token and refresh token
        // to/from "oauth_access_token" and "oauth_refresh_token" table.
        return JdbcTokenStore(dataSource)
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        return JwtAccessTokenConverter()
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.authenticationManager(authenticationManager)
            .accessTokenConverter(jwtAccessTokenConverter())
            .tokenStore(tokenStore())
    }
}