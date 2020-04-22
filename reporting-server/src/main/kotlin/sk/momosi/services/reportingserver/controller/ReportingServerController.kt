package sk.momosi.services.reportingserver.controller

import org.apache.pdfbox.pdmodel.PDDocument
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import sk.momosi.dto.employee.EmployeeDTO
import sk.momosi.services.Constants
import sk.momosi.services.reportingserver.clients.DataClient
import sk.momosi.services.reportingserver.service.ReportService
import sk.momosi.servicesinterfaces.ReportingServerApi
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
class ReportingServerController(
        private val dataClient: DataClient, // this should of course not be here but in service, WIP
        private val reportService: ReportService
): ReportingServerApi {

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

    override fun getReport(response: HttpServletResponse): StreamingResponseBody {
        return createPdfResponse(response, reportService.getReport())
    }

    // private functions

    private fun createPdfResponse(response: HttpServletResponse, document: PDDocument): StreamingResponseBody {
        response.contentType = Constants.MEDIA_PDF
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.pdf")

        return StreamingResponseBody {
            document.save(it)
            document.close()
        }
    }

    private fun size(): Int {
        return listOfEmployees.size
    }

}
