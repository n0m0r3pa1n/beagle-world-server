package com.nmp90.beagleworld.meetings

import com.nmp90.beagleworld.users.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.Distance
import org.springframework.data.geo.Metrics
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
class MeetingController {

    @Autowired
    private lateinit var meetingMapper: MeetingMapper

    @Autowired
    private lateinit var meetingService: MeetingService

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/meetings")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMeeting(@Valid @RequestBody meetingJson: MeetingJson): Mono<Meeting> {
        return userService.getCurrentUser()
                .map { user -> meetingMapper.toMeeting(meetingJson, user) }
                .flatMap { meeting -> meetingService.createMeeting(meeting) }
    }

    @GetMapping("/meetings")
    fun getMeetings(@RequestParam("lat") lat: Double,
                    @RequestParam("lng") lng: Double,
                    @RequestParam("distance") distance: Double): Mono<List<Meeting>> {

        return meetingService.getMeetingsNearby(lat, lng, Distance(distance, Metrics.KILOMETERS))
    }
}