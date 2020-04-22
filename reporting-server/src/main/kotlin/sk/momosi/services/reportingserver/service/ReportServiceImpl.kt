package sk.momosi.services.reportingserver.service

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import org.apache.pdfbox.pdmodel.PDPageContentStream
import org.apache.pdfbox.pdmodel.common.PDRectangle
import org.apache.pdfbox.pdmodel.font.PDType1Font
import org.springframework.stereotype.Service

@Service
class ReportServiceImpl: ReportService {

    override fun getReport(): PDDocument {
        val page = PDPage(PDRectangle.A4)
        val document = PDDocument()
        document.addPage(page)

        val cos = PDPageContentStream(document, page)
        cos.beginText()
        cos.setFont(PDType1Font.COURIER, 16F)
        cos.showText("test export")
        cos.endText()
        cos.close()

        return document
    }
}
