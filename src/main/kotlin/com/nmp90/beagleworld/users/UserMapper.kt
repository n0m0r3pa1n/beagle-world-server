package com.nmp90.beagleworld.users

class UserMapper {
    fun toUser(userJson: UserJson): User {
        return User(null, userJson.name, userJson.email)
    }
}