package sk.momosi.services.dataserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class DataServerApplication

fun main(args: Array<String>) {
    runApplication<DataServerApplication>(*args)
}
