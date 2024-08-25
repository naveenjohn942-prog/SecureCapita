package io.getarrays.securecapita.report;

import io.getarrays.securecapita.exception.ApiException;
import io.getarrays.securecapita.model.Customer;
import io.getarrays.securecapita.model.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import static java.util.stream.IntStream.range;
import static org.apache.commons.lang3.time.DateFormatUtils.format;

@Slf4j
public class InvoiceReport {
    public static final String DATE_FORMATTER = "yyyy-MM-dd hh:mm:ss";
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Invoice> invoice;
    private static String[] HEADERS = { "Invoice Number", "Service", "Status", "Date", "Total"};

    public InvoiceReport(List<Invoice> invoice) {
        this.invoice = invoice;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Invoice");
        setHeaders();
    }

    private void setHeaders() {
        Row headerRow = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        style.setFont(font);
        range(0, HEADERS.length).forEach(index -> {
            Cell cell = headerRow.createCell(index);
            cell.setCellValue(HEADERS[index]);
            cell.setCellStyle(style);
        });
    }
    public InputStreamResource export() {
        return generateReport();
    }

    private InputStreamResource generateReport() {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontHeight(10);
            style.setFont(font);
            int rowIndex = 1;
            for (Invoice invoice1 : invoice) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(invoice1.getInvoiceNumber());
                row.createCell(1).setCellValue(invoice1.getServices());
                row.createCell(2).setCellValue(invoice1.getStatus());
                row.createCell(3).setCellValue(invoice1.getDate());
                row.createCell(4).setCellValue(invoice1.getTotal());

//                // Null check for date
//                if (invoice1.getDate() != null) {
//                    row.createCell(5).setCellValue(format(invoice1.getDate(), DATE_FORMATTER));
//                } else {
//                    row.createCell(5).setCellValue("N/A");
//                }
            }
            workbook.write(out);
            return new InputStreamResource(new ByteArrayInputStream(out.toByteArray()));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("Unable to export report file");
        }
    }
}
