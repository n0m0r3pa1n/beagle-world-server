package com.nmp90.beagleworld.dogs

import com.nmp90.beagleworld.users.FacebookTokenValidator
import com.nmp90.beagleworld.users.User
import com.nmp90.beagleworld.users.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class DogService {

    @Autowired
    private lateinit var repository: DogRepository

    fun createDog(dog: Dog): Mono<Dog> {
        return repository.insert(dog)
    }
}