package Marriage.pdfExcelImg;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class EXcellReader  {


    @Test
    public void metgh(){
        List<HashMap<String, String>> excel = excel();
        System.out.println(excel.get(0));
    }

    public List<HashMap<String, String>> excel() {
        try {
            FileInputStream fis = new FileInputStream("src/test/java/Marriage/pdfExcelImg/Book1.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet("Sheet1");
            int totalRows = sheet.getPhysicalNumberOfRows();
            System.out.println(totalRows);

            List<String> keys = new ArrayList<>();
            LinkedHashMap<String,String> hashmap;
            List<HashMap<String, String>> list = new ArrayList<>();

            DataFormatter df = new DataFormatter();
            for (int i = 0; i < totalRows; i++) {
                hashmap = new LinkedHashMap<>();
                if (i == 0) {
                    int physicalNumberOfCells = sheet.getRow(0).getPhysicalNumberOfCells();
                    for (int colNum = 0; colNum < physicalNumberOfCells; colNum++) {
                        String header = sheet.getRow(0).getCell(colNum).getStringCellValue();
                        keys.add(header);
                    }
                } else {
                    for (int colNum = 0; colNum < keys.size(); colNum++) {
                        String cell = df.formatCellValue(sheet.getRow(i).getCell(colNum));
                        hashmap.put(keys.get(colNum), cell);
                    }
                    list.add(hashmap);
                }
            }
            return list;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
