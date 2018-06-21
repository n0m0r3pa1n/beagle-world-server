package com.nmp90.beagleworld.meetings

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Distance
import org.springframework.stereotype.Service
import org.springframework.data.geo.Point
import reactor.core.publisher.Mono

@Service
class MeetingService {

    @Autowired
    private lateinit var meetingsRepository: MeetingsRepository

    fun getMeetingsNearby(lat: Double, lng: Double, distance: Distance): Mono<List<Meeting>> {
        val point = Point(lat, lng)
        return meetingsRepository.findByLocationNear(point, distance)
    }

    fun createMeeting(meeting: Meeting): Mono<Meeting> {
        return meetingsRepository.insert(meeting)
    }
}