package com.nmp90.beagleworld.exception

import org.springframework.http.HttpStatus

data class CustomException(override val message: String, val httpStatus: HttpStatus) : RuntimeException() {

}