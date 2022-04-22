package com.example.app.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler

@Configuration
@EnableResourceServer
class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {
    override fun configure(resources: ResourceServerSecurityConfigurer) {
        resources.resourceId(RESOURCE_ID).stateless(true)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.anonymous()
            .and()
            .authorizeRequests()
            .antMatchers("/").permitAll() // Make sure anonymous() is enabled
            .antMatchers("/auth/signup").permitAll()
            .antMatchers("/auth/signout").authenticated()
            .antMatchers("/me").authenticated()
            .anyRequest().authenticated()
            .and().exceptionHandling().accessDeniedHandler(OAuth2AccessDeniedHandler())
    }

    companion object {
        private const val RESOURCE_ID = "resource_id"
    }
}