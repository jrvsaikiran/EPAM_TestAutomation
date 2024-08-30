package Marriage.pdfExcelImg;

import Marriage.Bharath.Dp_data;
import Marriage.Bharath.FolderPaths;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelToHtml {
    public  void htmlReport() {

        String excelFilePath = Dp_data.excelReport;
        String htmlFilePath = FolderPaths.HTML+"/HtmlReport.html";

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(htmlFilePath))) {

            Sheet sheet = workbook.getSheetAt(0);
            writer.println("<html><body><table border='1'>");

            for (Row row : sheet) {
                writer.println("<tr>");
                for (Cell cell : row) {
                    writer.print("<td>");
                    switch (cell.getCellType()) {
                        case STRING:
                            writer.print(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            writer.print(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            writer.print(cell.getBooleanCellValue());
                            break;
                        default:
                            writer.print("");
                    }
                    writer.println("</td>");
                }
                writer.println("</tr>");
            }

            writer.println("</table></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

