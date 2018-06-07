package com.nmp90.beagleworld.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService {

    @Autowired
    lateinit var repository: UserRepository

    fun getUsers(): Flux<User> {
        return repository.findAll()
    }

    fun createUser(user: User): Mono<User> {
        return repository.insert(user)
    }
}