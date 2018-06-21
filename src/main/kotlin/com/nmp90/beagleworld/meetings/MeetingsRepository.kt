package com.nmp90.beagleworld.meetings

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import org.springframework.data.geo.Distance
import org.springframework.data.geo.GeoResults
import org.springframework.data.geo.Point


@Repository
interface MeetingsRepository : ReactiveMongoRepository<Meeting, String> {
    fun findByLocationNear(p: Point, d: Distance): Mono<GeoResults<Meeting>>
}