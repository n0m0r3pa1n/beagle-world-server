package com.nmp90.beagleworld.dogs

import com.nmp90.beagleworld.exception.CustomException
import com.nmp90.beagleworld.security.JwtTokenProvider
import com.nmp90.beagleworld.users.User
import com.nmp90.beagleworld.users.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
class DogController {

    @Autowired
    private lateinit var dogServiceService: DogService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var dogMapper: DogMapper

    @PostMapping("/users/{id}/dogs")
    fun createDog(@Valid @RequestBody dogJson: DogJson, @PathVariable("id") userId: String): Mono<Dog> {
        return userService.findUserById(userId)
                .flatMap { user ->
                    val dog = dogMapper.toDog(dogJson, user.id!!)
                    dogServiceService.createDog(dog)
                            .flatMap { updateUserDogs(user, dog) }
                            .flatMap { Mono.just(dog) }
                }

    }

    private fun updateUserDogs(user: User, dog: Dog): Mono<User> {
        user.dogs.add(dog)
        return userService.updateUser(user)
    }
}