package com.nmp90.beagleworld.users

import javax.validation.constraints.NotEmpty

data class UserRefreshJson (@get:NotEmpty val accessToken: String,
                            @get:NotEmpty val token: String,
                            @get:NotEmpty val socialId: String,
                            val socialNetwork: SocialNetworks = SocialNetworks.FACEBOOK)