package sk.momosi.services.employeeserver.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import sk.momosi.servicesinterfaces.EmployeeServerApi

@RestController
class EmployeeServerController: EmployeeServerApi {

    val listOfEmployees: Map<Long, String> = mapOf(
            1L to "Michael Jackson",
            2L to "Freddie Mercury",
            3L to "Billy Idol",
            4L to "Madonna",
            5L to "Stevie Wonder"
    )

    override fun addEmployee(employee: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEmployee(id: Long): ResponseEntity<String> {
        val result = listOfEmployees.get(id % size() + 1)
        if (result == null) {
            return ResponseEntity.badRequest().body("Cannot find Employee with id $id")
        }
        return ResponseEntity.ok(result)
    }

    override fun getEmployees(): ResponseEntity<List<String>> {
        return ResponseEntity.ok(listOfEmployees.map { e -> "${e.key} -> ${e.value}" })
    }

    private fun size(): Int {
        return listOfEmployees.size
    }

}
