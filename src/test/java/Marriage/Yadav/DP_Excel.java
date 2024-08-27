package Marriage.Yadav;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class DP_Excel {

    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static int rowCount = 1;

    public void readData(LinkedHashMap<Integer, List<CustomerData>> map, int i) {

        try {
            if (wb == null) {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("test");
            }
            //create headers
            XSSFRow h1 = sheet.createRow(0);
            h1.createCell(0).setCellValue("Name");
            h1.createCell(1).setCellValue("Age");
            h1.createCell(2).setCellValue("Education");
            h1.createCell(3).setCellValue("Location");
            h1.createCell(4).setCellValue("Activity");

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            FileOutputStream fis = new FileOutputStream("src/test/java/Marriage/Yadav/matahdata.xlsx");
            wb.write(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException---->>>>" + e);
        } catch (IOException e) {
            throw new RuntimeException("IOException----->>>" + e);
        }


    }
}