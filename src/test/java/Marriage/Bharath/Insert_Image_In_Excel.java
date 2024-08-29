package Marriage.Bharath;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.Base64;

public class Insert_Image_In_Excel {
    public static  void main() throws IOException {

//        google();

        //create a new workbook
        XSSFWorkbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();
//add picture data to this workbook.
        String pic = "src/test/java/Marriage/Bharath/Screenshots/image.png";
        FileInputStream is = new FileInputStream(pic);

//        byte[] bytes = IOUtils.toByteArray(is);
        byte[] bytes = FileUtils.readFileToByteArray(new File(pic));
        String s = Base64.getEncoder().encodeToString(bytes);
        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);


        is.close();
        XSSFCreationHelper helper = wb.getCreationHelper();
//create sheet
        XSSFSheet sheet = wb.createSheet("pic");
// Create the drawing patriarch.  This is the top level container for all shapes.
        Drawing drawing = sheet.createDrawingPatriarch();
//add a picture shape
        XSSFClientAnchor anchor = helper.createClientAnchor();
//set top-left corner of the picture,
//subsequent call of Picture#resize() will operate relative to it
        anchor.setCol1(1);
        anchor.setRow1(1);
        Picture pict = drawing.createPicture(anchor, pictureIdx);
//auto-size picture relative to its top-left corner
        pict.getPreferredSize();
//save workbook
        String file = "src/test/java/Marriage/Bharath/bharathProfile.xlsx";
//        if(wb instanceof XSSFWorkbook) file += "x";
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            wb.write(fileOut);
            wb.close();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }

    private static void google() throws IOException {

        WebDriverManager.chromedriver();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//picture//img"));
        File screenshotAs = element.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(screenshotAs,new File("src/test/java/Marriage/Bharath/Screenshots/image.png"));


    }

}
