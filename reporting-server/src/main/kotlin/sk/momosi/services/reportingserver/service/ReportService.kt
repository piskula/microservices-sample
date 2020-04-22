package sk.momosi.services.reportingserver.service

import org.apache.pdfbox.pdmodel.PDDocument

interface ReportService {

    fun getReport(): PDDocument

}
