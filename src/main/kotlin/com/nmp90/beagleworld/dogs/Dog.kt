package com.nmp90.beagleworld.dogs

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.constraints.NotEmpty

@Document(collection = "dog")
data class Dog(@Id val id: String?,
               val name: String,
               val picture: String?,
               val dateOfBirth: Date?,
               val owners: List<ObjectId>)