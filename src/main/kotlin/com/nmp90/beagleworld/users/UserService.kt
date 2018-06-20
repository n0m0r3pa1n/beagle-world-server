package com.nmp90.beagleworld.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import com.nmp90.beagleworld.security.JwtTokenProvider
import com.sun.org.apache.xpath.internal.operations.Bool


@Service
class UserService {

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var facebookTokenValidator: FacebookTokenValidator

    fun getUsers(): Flux<User> {
        return repository.findAll()
    }

    fun createUser(user: User): Mono<User> {
        return repository.insert(user)
    }

    fun findUser(user: User): Mono<User> {
        return repository.findOneByEmail(user.email)
    }

    fun findUserBySocialId(socialId: String): Mono<User> {
        return repository.findOneBySocialId(socialId)
    }

    fun isValidUser(socialId: String, token: String): Mono<Boolean> {
        return facebookTokenValidator.isValidUser(socialId, token)
    }
}