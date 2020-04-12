package sk.momosi.services.dataserver.dto

import sk.momosi.dto.data.SensorDTO
import sk.momosi.dto.data.SensorValueDTO
import sk.momosi.services.dataserver.entity.SensorValue
import java.time.LocalDateTime

data class SensorValueDTOImpl (
        override val id: Long?,
        override val sensor: SensorDTO?,
        override val timestamp: LocalDateTime?,
        override val value: Double?
): SensorValueDTO {
    companion object {

        fun getDtoFrom(sensorValue: SensorValue): SensorValueDTOImpl {
            with(sensorValue) {
                return SensorValueDTOImpl(
                        id,
                        if (sensor == null) null else SensorDTOImpl.getDtoFrom(sensor),
                        timestamp,
                        value)
            }
        }

        fun getDtoFrom(sensorValues: Iterable<SensorValue>): Collection<SensorValueDTOImpl> {
            return sensorValues.map { getDtoFrom(it) }
        }
    }
}
