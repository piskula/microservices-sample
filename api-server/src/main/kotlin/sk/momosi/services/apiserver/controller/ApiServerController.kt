package sk.momosi.services.apiserver.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController
import sk.momosi.servicesinterfaces.ApiServerApi
import sk.momosi.servicesinterfaces.DataServerApi
import java.time.LocalDateTime

@RestController
class ApiServerController: ApiServerApi {

    @Autowired
    lateinit var dataServer: DataServerApi


    override fun getTestConfig(): String? {
        return dataServer.getJvm() ?: "no-response"
    }

    override fun getDate(): LocalDateTime {
        return dataServer.getDateJvm()
    }

    override fun getTimestamp(): Long {
        return dataServer.getLongJvm()
    }

}
