package com.nmp90.beagleworld.dogs

import org.bson.types.ObjectId
import org.springframework.stereotype.Component

@Component
class DogMapper {
    fun toDog(dogJson: DogJson, ownerId: String): Dog {
        return Dog(null, dogJson.name, dogJson.picture, dogJson.dateOfBirth, arrayListOf(ObjectId(ownerId)))
    }
}