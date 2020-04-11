package sk.momosi.services.dataserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
class DataServerApplication

fun main(args: Array<String>) {
    runApplication<DataServerApplication>(*args)
}
