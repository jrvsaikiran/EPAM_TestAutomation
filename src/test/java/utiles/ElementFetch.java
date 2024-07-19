package utiles;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class ElementFetch extends BaseTest {


    public ElementFetch() {
        super();
    }

    public WebElement getWebElement(String type, String value, WebDriver driver) {

        switch (type) {
            case "id":
                return driver.findElement(By.id(value));
            case "name":
                return driver.findElement(By.name(value));
            case "link":
                return driver.findElement(By.linkText(value));
            case "xpath":
                return driver.findElement(By.xpath(value));
            case "css":
                return driver.findElement(By.cssSelector(value));
            default:
                return null;
        }

    }

    public String takeScreenShot(WebDriver driver) {
        Random random = new Random();
        int i = random.nextInt();
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + i + ".png";

        String bytePic;
        try {
            FileUtils.copyFile(source, new File(destination));  //take screnshot and stored
            byte[] bytes = FileUtils.readFileToByteArray(new File(destination));    //convert to base64
            bytePic = Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            throw new RuntimeException("Unable to take screen shot" + e.getLocalizedMessage());
        }
        return bytePic;
    }




    public List<WebElement> getElements(String type, String value) {
        switch (type) {
            case "id":
                return driver.findElements(By.id(value));
            case "name":
                return driver.findElements(By.name(value));
            case "link":
                return driver.findElements(By.linkText(value));
            case "xpath":
                return driver.findElements(By.xpath(value));
            case "css":
                return driver.findElements(By.cssSelector(value));
            default:
                return null;
        }
    }


}
