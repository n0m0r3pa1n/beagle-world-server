package com.nmp90.beagleworld.meetings

import com.nmp90.beagleworld.users.User
import org.springframework.data.mongodb.core.geo.GeoJsonPoint

import org.springframework.stereotype.Component

@Component
class MeetingMapper {
    fun toMeeting(meetingJson: MeetingJson, organizer: User): Meeting {
        val location = GeoJsonPoint(meetingJson.lat, meetingJson.lng)

        return Meeting(null, meetingJson.name, arrayListOf(organizer), meetingJson.startDate, meetingJson.endDate,
                location, arrayListOf())
    }
}