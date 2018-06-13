package com.nmp90.beagleworld.users

import javax.validation.constraints.NotEmpty

data class UserJson (@get:NotEmpty val name: String,
                     @get:NotEmpty val email: String,
                     @get:NotEmpty val accessToken: String,
                     @get:NotEmpty val socialId: String,
                     val profilePicture: String?,
                     val socialNetwork: SocialNetworks = SocialNetworks.FACEBOOK)