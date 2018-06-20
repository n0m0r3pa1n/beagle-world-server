package com.nmp90.beagleworld.users

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono



@Repository
interface UserRepository : ReactiveMongoRepository<User, String> {
    fun findOneByEmail(email: String): Mono<User>

    fun findOneBySocialId(socialId: String): Mono<User>
}