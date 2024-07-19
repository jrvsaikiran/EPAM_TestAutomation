package dataprovider;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

public class DataProvide {

    public static List<String[]> getList;

    @DataProvider(name = "facebook")
    public static Object[][] readData() {
        String[][] testdata = new String[0][];

        try {
            FileInputStream fis = new FileInputStream("src/test/java/dataprovider/facebook.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            XSSFSheet sheet = wb.getSheet("Sheet1");
            int lastRowNum = sheet.getLastRowNum();
            int lastCellNum = sheet.getRow(0).getLastCellNum();
            System.out.println("Row Count " + lastRowNum+" and Column Count " + lastCellNum);

            testdata = new String[lastRowNum][lastCellNum];
            DataFormatter formate = new DataFormatter();

            for (int i = 1; i <= lastRowNum; i++) {
                for (int j = 0; j < lastCellNum; j++) {
                    testdata[i - 1][j] = formate.formatCellValue(sheet.getRow(i).getCell(j));
//                    System.out.println("The value of row " + (i - 1) + " and column " + j + " is : " + testdata[i - 1][j]);
                }
            }
        } catch (Exception e) {

        }
        getList = Arrays.stream(testdata).toList();
//        System.out.println("List " + list.get(1));
        return testdata;
    }



}
