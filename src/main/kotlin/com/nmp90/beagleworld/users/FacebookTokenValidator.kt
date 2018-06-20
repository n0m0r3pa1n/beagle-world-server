package com.nmp90.beagleworld.users

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class FacebookTokenValidator {
    fun isValidUser(socialId: String, token: String): Mono<Boolean> {
        return Mono.just(true)
    }

}