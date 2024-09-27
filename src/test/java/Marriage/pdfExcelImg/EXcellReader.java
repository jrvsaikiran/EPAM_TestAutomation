package Marriage.pdfExcelImg;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class EXcellReader  {


    @Test
    public void sortByKeys_Values(){
        List<LinkedHashMap<String, String>> excel = excel();
        Consumer<List<LinkedHashMap<String, String>>> function = (e)-> {
            for (LinkedHashMap<String, String> row : e) {
                Set<Map.Entry<String, String>> entries = row.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    if(entry.getValue().contains("Software")){
                        System.out.println(entry.getValue());
//                        System.out.println(entries);
                    }
                }
            }
        };
        function.accept(excel);

    }

    @Test
    private void sortByAge() {
        List<LinkedHashMap<String, String>> excel = excel();
        Collections.sort(excel, (o1, o2) -> o2.get("age").compareTo(o1.get("age")));
        for (LinkedHashMap<String, String> row : excel) {
            System.out.println(row);
        }
    }

    @Test
    private void sortByName() {
        List<LinkedHashMap<String, String>> excel = excel();
        Collections.sort(excel, (o1, o2) -> o2.get("name").compareTo(o1.get("name")));
        for (LinkedHashMap<String, String> row : excel) {
            System.out.println(row);
        }
    }

    public List<LinkedHashMap<String, String>> excel() {
        try {
            FileInputStream fis = new FileInputStream("src/test/java/Marriage/pdfExcelImg/Book1.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheet("Sheet1");
            int totalRows = sheet.getPhysicalNumberOfRows();
            System.out.println(totalRows);

            List<String> keys = new ArrayList<>();
            LinkedHashMap<String,String> hashmap;
            List<LinkedHashMap<String, String>> list = new ArrayList<>();

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
