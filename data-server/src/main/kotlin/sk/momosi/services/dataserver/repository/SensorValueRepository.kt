package sk.momosi.services.dataserver.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sk.momosi.services.dataserver.entity.SensorValue

@Repository
interface SensorValueRepository : CrudRepository<SensorValue, Long> {

    fun findBySensorIdOrderByTimestampDesc(sensorId: Long, pageable: Pageable): Page<SensorValue>

}
