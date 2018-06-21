package com.nmp90.beagleworld.dogs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class DogService {

    @Autowired
    private lateinit var dogMapper: DogMapper

    @Autowired
    private lateinit var repository: DogsRepository

    fun createDog(dog: Dog): Mono<Dog> {
        return repository.insert(dog)
    }
}