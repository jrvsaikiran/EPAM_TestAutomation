package Marriage.Yadav;

import Marriage.Bharath.FolderPaths;
import Marriage.Bharath.MatrimonyTesting;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class DP_Excel {

    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static int rowCount = 1;
    private static File file;
    private static String excelReport;
    public void readData(LinkedHashMap<Integer, List<CustomerData>> map, int i) {

        try {
            if (wb == null) {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("yadav");
            }
            //create headers
            XSSFRow h1 = sheet.createRow(0);
            h1.createCell(0).setCellValue("Name");
            h1.createCell(1).setCellValue("Age");
            h1.createCell(2).setCellValue("Education");
            h1.createCell(3).setCellValue("Location");
            h1.createCell(4).setCellValue("Activity");
            h1.createCell(5).setCellValue("Profile Number");

            List<CustomerData> customerData = map.get(i);
            CustomerData data = customerData.get(0);

            //create row data
            XSSFRow row = sheet.createRow(rowCount);
            rowCount++;
            row.createCell(0).setCellValue(data.getName());
            row.createCell(1).setCellValue(data.getAge());
            row.createCell(2).setCellValue(data.getEducation());
            row.createCell(3).setCellValue(data.getLocation());
            row.createCell(4).setCellValue(data.getActivity());
            row.createCell(5).setCellValue(data.getProfileNumber());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        MatrimonyTesting par = new MatrimonyTesting();

        try {
            if (file==null) {
                file = new File(FolderPaths.EXCEL);
                file.mkdir();
            }
            if(par.getTestngXml().equalsIgnoreCase("mixed.xml")){
                excelReport = file + "/MixedExcelReport.xlsx";
            }else {
                excelReport = file + "/YadavExcelReport.xlsx";
            }


            FileOutputStream fis = new FileOutputStream(excelReport);
            wb.write(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException---->>>>" + e);
        } catch (IOException e) {
            throw new RuntimeException("IOException----->>>" + e);
        }


    }
}
