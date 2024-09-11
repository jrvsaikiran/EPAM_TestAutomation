package Marriage.Bharath;

import Marriage.pdfExcelImg.Convert_Image_To_PDF;
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
import java.util.List;


public class Bharath_DataProvider {

    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static  int i = 1;
    private static ProfileData profileData;
    private static File file;
    public static String excelReport;
    public void readBharathData(List<ProfileData> list, String imageName){

        try {
            if (wb == null) {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("bharathData");
            }
            //create headers
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("Age");
            header.createCell(2).setCellValue("Cast");
            header.createCell(3).setCellValue("Education");
            header.createCell(4).setCellValue("Location");
            header.createCell(5).setCellValue("Activity");
            header.createCell(6).setCellValue("Hyper Links pdfs");
            header.createCell(7).setCellValue("Records- "+ BharathFunctions.totalRecords);

             profileData = list.get(0);
            //create row data
            XSSFRow row = sheet.createRow(i);
            i++;
            row.createCell(0).setCellValue(profileData.getName());
            row.createCell(1).setCellValue(profileData.getAge());
            row.createCell(2).setCellValue(profileData.getCast());
            row.createCell(3).setCellValue(profileData.getEducation());
            row.createCell(4).setCellValue(profileData.getLocation());
            row.createCell(5).setCellValue(profileData.getActivity());


            hyperLinkCreation(row,imageName);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            if (file==null) {
                file = new File(FolderPaths.EXCEL);
                file.mkdir();
            }
             excelReport = file + "/BharathExcelReport.xlsx";

            FileOutputStream fis = new FileOutputStream(excelReport);
            wb.write(fis);
            System.out.println("Excel Written success");

        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException---->>>>" + e);
        } catch (IOException e) {
            throw new RuntimeException("IOException----->>>" + e);
        }


    }

    private static void hyperLinkCreation(XSSFRow row, String imageName) {
        XSSFCell cell = row.createCell(6);
        cell.setCellValue(profileData.getName());
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