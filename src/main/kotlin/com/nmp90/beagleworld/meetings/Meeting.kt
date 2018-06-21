package com.nmp90.beagleworld.meetings

import com.nmp90.beagleworld.users.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.geo.GeoJsonPoint
import org.springframework.data.mongodb.core.mapping.Document

import java.util.*

@Document(collection = "meeting")
data class Meeting(@Id val id: String?,
                   val name: String,
                   val organizers: List<User>,
                   val startDate: Date,
                   val endDate: Date,
                   val location: GeoJsonPoint,
                   val participants: List<User>)