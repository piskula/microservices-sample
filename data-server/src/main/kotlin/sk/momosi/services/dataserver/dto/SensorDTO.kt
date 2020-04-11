package sk.momosi.services.dataserver.dto

import sk.momosi.services.dataserver.entity.Sensor

class SensorDTO (
        val id: Long?,
        val name: String?
) {
    companion object {

        fun getDtoFrom(sensor: Sensor): SensorDTO {
            with(sensor) {
                return SensorDTO(id, name)
            }
        }

        fun getDtoFrom(sensors: Iterable<Sensor>): Collection<SensorDTO> {
            return sensors.map { getDtoFrom(it) }
        }
    }

}
