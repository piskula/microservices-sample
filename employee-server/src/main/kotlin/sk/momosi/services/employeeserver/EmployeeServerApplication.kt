package sk.momosi.services.employeeserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class EmployeeServerApplication

fun main(args: Array<String>) {
    runApplication<EmployeeServerApplication>(*args)
}
