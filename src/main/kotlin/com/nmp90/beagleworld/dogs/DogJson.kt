package com.nmp90.beagleworld.dogs

import java.util.*
import javax.validation.constraints.NotEmpty

data class DogJson(@get:NotEmpty val name: String,
                   val picture: String?,
                   val dateOfBirth: Date?)