package sk.momosi.services.dataserver.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import sk.momosi.services.dataserver.dto.SensorDTO
import sk.momosi.services.dataserver.dto.SensorValueCreateDTO
import sk.momosi.services.dataserver.dto.SensorValueDTO
import sk.momosi.services.dataserver.repository.SensorRepository
import sk.momosi.services.dataserver.repository.SensorValueRepository
import sk.momosi.servicesinterfaces.DataServerApi
import java.time.*
import javax.validation.Valid

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

    @GetMapping("/lastvalueforsensor")
    fun getLastValueForSensor(@RequestParam sensor: Long): ResponseEntity<SensorValueDTO> {
        val result = sensorValueRepo.findBySensorIdOrderByTimestampDesc(sensor, FIRST_RESULT)
        if (result.isEmpty) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok(SensorValueDTO.getDtoFrom(result.content.get(0)))
    }

    @GetMapping("/sensors")
    fun getSensors(): Collection<SensorDTO> {
        return SensorDTO.getDtoFrom(sensorRepo.findAll())
    }


    @PostMapping("/sensorvalue")
    fun postNewSensorValue(@Valid @RequestBody sensorValue: SensorValueCreateDTO): ResponseEntity<SensorValueDTO> {
        if (sensorValue.timestamp == null) {
            sensorValue.timestamp = LocalDateTime.now()
        }

        try {
            return ResponseEntity.ok(
                    SensorValueDTO.getDtoFrom(
                            sensorValueRepo.save(sensorValue.toEntity())))
        } catch (e: Exception) {
            return ResponseEntity.unprocessableEntity().build()
        }
    }

}
