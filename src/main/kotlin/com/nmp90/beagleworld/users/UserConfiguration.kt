package com.nmp90.beagleworld.users

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfiguration {
    @Bean("mapper_generator")
    fun getMapper() = UserMapper()
}