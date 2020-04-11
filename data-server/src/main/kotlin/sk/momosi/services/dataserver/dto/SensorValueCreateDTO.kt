package sk.momosi.services.dataserver.dto

import sk.momosi.services.dataserver.entity.Sensor
import sk.momosi.services.dataserver.entity.SensorValue
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

data class SensorValueCreateDTO (
        @get:NotNull val sensorId: Long?,
        @get:NotNull val sensorValue: Double?,
        var timestamp: LocalDateTime?
) {
    fun toEntity(): SensorValue {
        return SensorValue(null, Sensor(sensorId, null), timestamp, sensorValue)
    }
}
