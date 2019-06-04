package sk.momosi.services.apiserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("sk.momosi.services.apiserver")
class ApiServerApplication

fun main(args: Array<String>) {
	runApplication<ApiServerApplication>(*args)
}
