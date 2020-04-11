package sk.momosi.services.dataserver.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sk.momosi.services.dataserver.entity.Sensor

@Repository
interface SensorRepository : CrudRepository<Sensor, Long>
