package com.nmp90.beagleworld.meetings

import java.util.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class MeetingJson(@get:NotEmpty val name: String,
                       @get:NotNull val lat: Double,
                       @get:NotNull val lng: Double,
                       @get:NotNull val startDate: Date,
                       @get:NotNull val endDate: Date)