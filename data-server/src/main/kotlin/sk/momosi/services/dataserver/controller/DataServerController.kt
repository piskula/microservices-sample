package sk.momosi.services.dataserver.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import sk.momosi.servicesinterfaces.DataServerApi
import java.time.LocalDateTime
import java.time.ZoneOffset

@RestController
class DataServerController: DataServerApi {

    // configuration property value
    @Value("\${message}")
    val message: String? = null


    override fun getDateJvm(): LocalDateTime {
        return LocalDateTime.now()
    }

    override fun getJvm(): String? {
        return message ?: ""
    }

    override fun getLongJvm(): Long {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    }

}
