package utiles;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;
import static base.BaseTest.driver;
import static base.BaseTest.nameOfBrowser;

public class ElementFetch extends BaseTest {

    public static WebElement getWebElement(String type, String value) throws Exception {

        switch (type) {
            case "id":
                logger.info("id: " + value);
                return driver.findElement(By.id(value));
            case "name":
                logger.info("name: " + value);
                return driver.findElement(By.name(value));
            case "link":
                logger.info("link: " + value);
                return driver.findElement(By.linkText(value));
            case "xpath":
                logger.info("xpath: " + value);
                return driver.findElement(By.xpath(value));
            case "css":
                logger.info("css: " + value);
                return driver.findElement(By.cssSelector(value));
            default:
                logger.info("Inavlid type:---> " + type);
                throw new Exception("Inavlid type:---> " + type);
        }

    }

    public String takeScreenShot() {
        Date date  = new Date();
        long time = date.getTime();
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") +File.separator+"target"+ File.separator + "screenshots" + File.separator +nameOfBrowser+ time + ".jpeg";

        String bytePic;
        try {
            FileUtils.copyFile(source, new File(destination));  //take screnshot and stored
            byte[] bytes = FileUtils.readFileToByteArray(new File(destination));    //convert to base64
            bytePic = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Unable to take screen shot" + e.getLocalizedMessage());
        }
        logger.info("Screenshot taken: " + destination);
        return bytePic;
    }




    public List<WebElement> getElements(String type, String value) throws Exception {
        switch (type) {
            case "id":
                logger.info("id: " + value);
                return driver.findElements(By.id(value));
            case "name":
                logger.info("name: " + value);
                return driver.findElements(By.name(value));
            case "link":
                logger.info("link: " + value);
                return driver.findElements(By.linkText(value));
            case "xpath":
                logger.info("xpath: " + value);
                return driver.findElements(By.xpath(value));
            case "css":
                logger.info("css: " + value);
                return driver.findElements(By.cssSelector(value));
            default:
                logger.info("Inavlid type:---> " + type);
                throw new Exception("Inavlid type: " + type);
        }
    }


}
