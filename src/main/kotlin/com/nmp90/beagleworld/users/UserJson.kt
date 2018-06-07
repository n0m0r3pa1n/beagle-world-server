package com.nmp90.beagleworld.users

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class UserJson (@get:NotEmpty val name: String, @get:NotNull val email: String)