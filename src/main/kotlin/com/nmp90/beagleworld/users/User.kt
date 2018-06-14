package com.nmp90.beagleworld.users

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotEmpty

@Document(collection = "user")
data class User(@Id val id: String?,
                val name: String?,
                val profilePicture: String?,
                @Indexed(unique = true) val email: String,
                val socialId: String,
                @get:NotEmpty val roles: List<Role>,
                val socialNetwork: SocialNetworks,
                val accessToken: String)