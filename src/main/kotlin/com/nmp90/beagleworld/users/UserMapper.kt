package com.nmp90.beagleworld.users

import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toUser(userJson: UserJson, roles: List<Role>) = User(null, userJson.name, userJson.profilePicture,
            userJson.email, userJson.socialId, roles,
            userJson.socialNetwork, userJson.accessToken)
}