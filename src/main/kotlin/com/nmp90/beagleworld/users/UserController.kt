package com.nmp90.beagleworld.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
class UserController {

    @Autowired
    lateinit var service: UserService

    @Autowired
    lateinit var userMapper: UserMapper

    @PostMapping("/users")
    fun createUser(@Valid @RequestBody userJson: UserJson): Mono<User> {
        val user = userMapper.toUser(userJson)
        return service.createUser(user)
    }

    @GetMapping("/users")
    fun getUsers(): Flux<User> {
        return service.getUsers()
    }
}