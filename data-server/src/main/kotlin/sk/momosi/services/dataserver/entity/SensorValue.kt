package sk.momosi.services.dataserver.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity(name = "sensor_value")
data class SensorValue(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,

        @ManyToOne(fetch = FetchType.LAZY)
        val sensor: Sensor?,

        @Column
        var timestamp: LocalDateTime?,

        @Column
        val value: Double?

)
