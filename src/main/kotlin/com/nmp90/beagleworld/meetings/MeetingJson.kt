package com.nmp90.beagleworld.meetings

import java.util.*
import javax.validation.constraints.NotEmpty

data class MeetingJson(@get:NotEmpty val name: String,
                       @get:NotEmpty val lat: Double,
                       @get:NotEmpty val lng: Double,
                       @get:NotEmpty val startDate: Date,
                       @get:NotEmpty val endDate: Date)