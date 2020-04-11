package sk.momosi.services.dataserver.dto

import sk.momosi.services.dataserver.entity.SensorValue
import java.time.LocalDateTime

data class SensorValueDTO (
        val id: Long?,
        val sensor: SensorDTO?,
        var timestamp: LocalDateTime?,
        var value: Double?
) {
    companion object {

        fun getDtoFrom(sensorValue: SensorValue): SensorValueDTO {
            with(sensorValue) {
                return SensorValueDTO(
                        id,
                        if (sensor == null) null else SensorDTO.getDtoFrom(sensor),
                        timestamp,
                        value)
            }
        }

        fun getDtoFrom(sensorValues: Iterable<SensorValue>): Collection<SensorValueDTO> {
            return sensorValues.map { getDtoFrom(it) }
        }
    }
}
