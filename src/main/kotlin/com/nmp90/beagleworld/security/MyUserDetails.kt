package com.nmp90.beagleworld.security

import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import com.nmp90.beagleworld.users.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class MyUserDetails : UserDetailsService {

    @Autowired
    private val userRepository: UserRepository? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository!!.findOneByEmail(username).block()
                ?: throw UsernameNotFoundException("User '$username' not found")

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(user.accessToken)//
                .authorities(user.roles)//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build()
    }

}