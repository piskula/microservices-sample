package sk.momosi.services.dataserver.dto.util

import sk.momosi.dto.data.SensorValueCreateDTO
import sk.momosi.services.dataserver.entity.Sensor
import sk.momosi.services.dataserver.entity.SensorValue

class DtoConversionUtil {

    companion object {

        fun toEntity(sensorValueCreate: SensorValueCreateDTO): SensorValue {
            with (sensorValueCreate) {
                return SensorValue(
                        null,
                        Sensor(sensorId, null),
                        timestamp,
                        sensorValue)
            }
        }

    }
}
