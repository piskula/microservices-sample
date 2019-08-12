package sk.momosi.services.dataserver.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import sk.momosi.servicesinterfaces.DataServerApi
import java.time.*

@RestController
class DataServerController: DataServerApi {

    // configuration property value
    @Value("\${error-message}")
    val messageErr: String? = null

    override fun getCustomErrorMessage(): String? {
        return messageErr ?: ""
    }

    override fun getLongJvm(): Long {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    }

    override fun getDateJvm(): LocalDate {
        return LocalDate.now()
    }

    override fun getDateTimeJvm(): LocalDateTime {
        return LocalDateTime.now()
    }

    override fun getOffsetDateTimeJvm(): OffsetDateTime {
        return OffsetDateTime.now()
    }

    override fun getZonedDateTimeJvm(): ZonedDateTime {
        return ZonedDateTime.now()
    }

}
