package sk.momosi.services.dataserver.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import sk.momosi.dto.data.SensorDTO
import sk.momosi.dto.data.SensorValueCreateDTO
import sk.momosi.dto.data.SensorValueDTO
import sk.momosi.services.dataserver.dto.SensorDTOImpl
import sk.momosi.services.dataserver.dto.SensorValueDTOImpl
import sk.momosi.services.dataserver.dto.util.DtoConversionUtil
import sk.momosi.services.dataserver.repository.SensorRepository
import sk.momosi.services.dataserver.repository.SensorValueRepository
import sk.momosi.servicesinterfaces.DataServerApi
import java.time.*

@RestController
class DataServerController @Autowired constructor(
        private val sensorValueRepo: SensorValueRepository,
        private val sensorRepo: SensorRepository
): DataServerApi {

    val FIRST_RESULT = PageRequest.of(0, 1)

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

    override fun getLastValueForSensor(sensor: Long): ResponseEntity<SensorValueDTO> {
        val result = sensorValueRepo.findBySensorIdOrderByTimestampDesc(sensor, FIRST_RESULT)
        if (result.isEmpty) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(SensorValueDTOImpl.getDtoFrom(result.content.get(0)))
    }

    override fun getSensors(): Collection<SensorDTO> {
        return SensorDTOImpl.getDtoFrom(sensorRepo.findAll())
    }

    override fun postNewSensorValue(sensorValue: SensorValueCreateDTO): ResponseEntity<SensorValueDTO> {
        if (sensorValue.timestamp == null) {
            sensorValue.timestamp = LocalDateTime.now()
        }

        val value = DtoConversionUtil.toEntity(sensorValue)
        try {
            return ResponseEntity.ok(
                    SensorValueDTOImpl.getDtoFrom(
                            sensorValueRepo.save(value)))
        } catch (e: Exception) {
            return ResponseEntity.unprocessableEntity().build()
        }
    }

}
