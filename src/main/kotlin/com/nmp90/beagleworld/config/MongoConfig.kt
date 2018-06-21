package com.nmp90.beagleworld.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@EnableReactiveMongoRepositories(basePackageClasses = arrayOf(MongoConfig::class))
class MongoConfig : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.uri}")
    internal var mongoUri: String? = null

    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create(mongoUri)
    }

    override fun getDatabaseName(): String {
        return "dogs"
    }

}