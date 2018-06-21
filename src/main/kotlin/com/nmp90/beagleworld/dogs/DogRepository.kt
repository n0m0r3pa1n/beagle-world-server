package com.nmp90.beagleworld.dogs

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono


@Repository
interface DogRepository : ReactiveMongoRepository<Dog, String> {

}