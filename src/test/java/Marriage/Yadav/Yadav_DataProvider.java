package Marriage.Yadav;

import Marriage.Bharath.FolderPaths;
import Marriage.pdfExcelImg.Convert_Image_To_PDF;
import Marriage.pdfExcelImg.Parameters;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class Yadav_DataProvider {

    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static int rowCount = 1;
    private static File file;
    private static String excelReport;
    private static CustomerData data;


    public void readData(LinkedHashMap<Integer, List<CustomerData>> map, int i, Parameters par, String imageName) {
        try {
            if (wb == null) {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet(par.getTestngXml());
            }
            //create headers
            XSSFRow h1 = sheet.createRow(0);
            h1.createCell(0).setCellValue("Name");
            h1.createCell(1).setCellValue("Age");
            h1.createCell(2).setCellValue("Education");
            h1.createCell(3).setCellValue("Location");
            h1.createCell(4).setCellValue("Activity");
            h1.createCell(5).setCellValue("Profile Number");
            h1.createCell(6).setCellValue("Hyper Links pdfs");

            List<CustomerData> customerData = map.get(i);
             data = customerData.get(0);

            //create row data
            XSSFRow row = sheet.createRow(rowCount);
            rowCount++;
            row.createCell(0).setCellValue(data.getName());
            row.createCell(1).setCellValue(data.getAge());
            row.createCell(2).setCellValue(data.getEducation());
            row.createCell(3).setCellValue(data.getLocation());
            row.createCell(4).setCellValue(data.getActivity());
            row.createCell(5).setCellValue(data.getProfileNumber());

            hyperLinkCreation(row,imageName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            if (file==null) {
                file = new File(FolderPaths.EXCEL);
                file.mkdir();
            }
            try {

                if(par.getTestngXml().equalsIgnoreCase("mixed.xml")){
                    excelReport = file + "/MixedExcelReport.xlsx";
                }else {
                    excelReport = file + "/YadavExcelReport.xlsx";
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            FileOutputStream fis = new FileOutputStream(excelReport);
            wb.write(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException---->>>>" + e);
        } catch (IOException e) {
            throw new RuntimeException("IOException----->>>" + e);
        }

        System.out.println("Excel Written success");

    }
    private static void hyperLinkCreation(XSSFRow row, String imageName) {
        XSSFCell cell = row.createCell(6);
        cell.setCellValue(data.getName());
        CreationHelper createHelper = wb.getCreationHelper();
        Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
        String pdfPath = Convert_Image_To_PDF.pdfPath;
//        System.out.println(pdfPath);
        link.setAddress(pdfPath);  // Path to the PDF file
        cell.setHyperlink(link);
        cell.setCellStyle(createHyperlinkStyle(wb));
    }

    private static CellStyle createHyperlinkStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setUnderline(Font.U_SINGLE);
        font.setColor(IndexedColors.BLUE.getIndex());
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
