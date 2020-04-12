package sk.momosi.services.dataserver.dto

import sk.momosi.dto.data.SensorDTO
import sk.momosi.services.dataserver.entity.Sensor

class SensorDTOImpl(
        override val id: Long?,
        override val name: String?
): SensorDTO {

    companion object {

        fun getDtoFrom(sensor: Sensor): SensorDTOImpl {
            with(sensor) {
                return SensorDTOImpl(
                        id,
                        name
                )
            }
        }

        fun getDtoFrom(sensors: Iterable<Sensor>): Collection<SensorDTOImpl> {
            return sensors.map { getDtoFrom(it) }
        }
    }

}
