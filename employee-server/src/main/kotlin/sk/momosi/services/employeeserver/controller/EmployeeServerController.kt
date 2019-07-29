package sk.momosi.services.employeeserver.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import sk.momosi.dto.employee.EmployeeDTO
import sk.momosi.services.employeeserver.clients.DataClient
import sk.momosi.servicesinterfaces.EmployeeServerApi
import java.util.*

@RestController
class EmployeeServerController: EmployeeServerApi {

    @Autowired
    lateinit var dataClient: DataClient

    val listOfEmployees: Map<Long, String> = mapOf(
            1L to "Michael Jackson",
            2L to "Freddie Mercury",
            3L to "Billy Idol",
            4L to "Madonna",
            5L to "Stevie Wonder"
    )

    override fun addEmployee(employee: String): ResponseEntity<Any> {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(dataClient.getCustomErrorMessage())
    }

    override fun getEmployee(id: Long): ResponseEntity<EmployeeDTO> {
        val result = listOfEmployees.get(id % size() + 1)
        if (result == null) {
            return ResponseEntity.badRequest().build()
        }
        return ResponseEntity.ok(
                EmployeeDTO(result, dataClient.getDateJvm(), UUID.randomUUID()))
    }

    override fun getEmployees(): ResponseEntity<List<EmployeeDTO>> {
        return ResponseEntity.ok(
                listOfEmployees.map { e ->
                    EmployeeDTO(
                            "${e.key} -> ${e.value}",
                            dataClient.getDateJvm(),
                            UUID.randomUUID())
                })
    }

    private fun size(): Int {
        return listOfEmployees.size
    }

}
