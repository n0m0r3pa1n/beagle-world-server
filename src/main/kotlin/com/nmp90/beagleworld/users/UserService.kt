package com.nmp90.beagleworld.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class UserService {

    @Autowired
    private lateinit var repository: UsersRepository

    @Autowired
    private lateinit var facebookTokenValidator: FacebookTokenValidator

    fun getUsers(): Flux<User> {
        return repository.findAll()
    }

    fun getCurrentUser(): Mono<User> {
        val loggedUser = SecurityContextHolder.getContext().authentication.principal as org.springframework.security.core.userdetails.User
        return repository.findOneByEmail(loggedUser.username)
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

    fun findUserById(userId: String): Mono<User> {
        return repository.findById(userId)
    }

    fun updateUser(user: User): Mono<User> {
        return repository.save(user)
    }
}