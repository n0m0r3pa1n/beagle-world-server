package com.nmp90.beagleworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages=["com.nmp90.beagleworld"])
class BeagleWorldApplication

fun main(args: Array<String>) {
    runApplication<BeagleWorldApplication>(*args)
}
