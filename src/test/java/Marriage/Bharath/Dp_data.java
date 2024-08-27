package Marriage.Bharath;

import Marriage.Yadav.CustomerData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Dp_data {

    private static XSSFWorkbook wb;
    private static XSSFSheet sheet;
    private static  int i = 1;

    public void readBharathData(List<ProfileData> list){

        try {
            if (wb == null) {
                wb = new XSSFWorkbook();
                sheet = wb.createSheet("bharathData");
            }
            //create headers
            XSSFRow h1 = sheet.createRow(0);
            h1.createCell(0).setCellValue("Name");
            h1.createCell(1).setCellValue("Age");
            h1.createCell(2).setCellValue("Cast");
            h1.createCell(3).setCellValue("Education");
            h1.createCell(4).setCellValue("Location");
            h1.createCell(5).setCellValue("Activity");

            ProfileData profileData = list.get(0);
            //create row data

            XSSFRow row = sheet.createRow(i);
            i++;
            row.createCell(0).setCellValue(profileData.getName());
            row.createCell(1).setCellValue(profileData.getAge());
            row.createCell(2).setCellValue(profileData.getCast());
            row.createCell(3).setCellValue(profileData.getEducation());
            row.createCell(4).setCellValue(profileData.getLocation());
            row.createCell(5).setCellValue(profileData.getActivity());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            FileOutputStream fis = new FileOutputStream("src/test/java/Marriage/Bharath/bharathProfile.xlsx");
            wb.write(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException---->>>>" + e);
        } catch (IOException e) {
            throw new RuntimeException("IOException----->>>" + e);
        }


    }
}
