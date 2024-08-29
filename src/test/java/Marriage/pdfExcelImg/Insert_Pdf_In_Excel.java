package Marriage.pdfExcelImg;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
public class Insert_Pdf_In_Excel {
    public static void main() {
        // Create a new Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        // Create a row and a cell
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(2);
        cell.setCellValue("Click to open PDF");
        // Create a hyperlink to the PDF file
        CreationHelper createHelper = workbook.getCreationHelper();
        Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress("C:/Users/rajavenkatasaikiran_/IdeaProjects/TestAutomation/src/test/java/Marriage/Bharath/PdfGenerated/dff.pdf");  // Path to the PDF file
        cell.setHyperlink(link);
        cell.setCellStyle(createHyperlinkStyle(workbook));
        // Save the Excel file
        try (FileOutputStream fileOut = new FileOutputStream("src/test/java/Marriage/Bharath/bharathProfile.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel file with PDF link created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Helper method to create a hyperlink style
    private static CellStyle createHyperlinkStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setUnderline(Font.U_SINGLE);
        font.setColor(IndexedColors.BLUE.getIndex());
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}


