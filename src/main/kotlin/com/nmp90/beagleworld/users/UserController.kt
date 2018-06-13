package com.nmp90.beagleworld.users

import com.nmp90.beagleworld.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
class UserController {

    @Autowired
    private lateinit var jwtTokenProvider: JwtTokenProvider

    @Autowired
    private lateinit var service: UserService

    @Autowired
    private lateinit var userMapper: UserMapper

    @PostMapping("/users")
    fun createUser(@Valid @RequestBody userJson: UserJson): Mono<ResponseEntity<User>>? {
        val user = userMapper.toUser(userJson, arrayListOf(Role.ROLE_CLIENT))
        return service.createUser(user)
                .map { newUser ->
                    val token = jwtTokenProvider.createToken(newUser.email, newUser.roles)
                    val headers = HttpHeaders()
                    headers.add("Bearer", token)
                    ResponseEntity(user, headers, HttpStatus.OK)

                }
    }

    @GetMapping("/test")
    fun getUsers(): Flux<User> {
        return service.getUsers()
    }
}