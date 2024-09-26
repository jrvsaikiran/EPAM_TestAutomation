package selenium4_features;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class S4_F {
     WebDriver driver;
    Random random = new Random();
    int i = random.nextInt(10000);


    @BeforeMethod
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @Test  //capture screenshot of element
    public void cap() throws IOException {
        driver.get("https://www.facebook.com/r.php?locale=en_US");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/img"));
        File sourse = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourse, new File("target/screenshots/capture" + i + ".png"));
    }

    @Test
    public void fullScrenShot() throws IOException, InterruptedException {
        driver.get("https://www.facebook.com/r.php?locale=en_US");
        Thread.sleep(2000);
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAs, new File("target/screenshots/capture" + i + ".png"));
    }

    @Test   //open new tab
    public void openNewTab() throws IOException {
        driver.get("https://www.facebook.com/r.php?locale=en_US");
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com");
    }

    @Test   //open new window
    public void openNewWindow() throws IOException {
        driver.get("https://www.facebook.com/r.php?locale=en_US");
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.google.com");
    }

    @Test
    public void locationOfElement() throws IOException {
        driver.get("https://www.facebook.com/r.php?locale=en_US");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/img"));
        Dimension dimension = element.getRect().getDimension();
        System.out.println(dimension.getWidth());
        System.out.println(dimension.getHeight());
        Point location = element.getLocation();
        System.out.println(location.getX() + " " + location.getY());
        System.out.println(location);
    }

    @Test
    public void xpathRelative() {
        driver.get("https://automationbookstore.dev/");
        WebElement element = driver.findElement(RelativeLocator.with(By.id("pid5_thumb")).toLeftOf(By.id("pid6_thumb")).below(By.id("pid1")));
        String id = element.getAttribute("id");
        Assert.assertEquals("pid5_thumb", id);
        System.out.println(id);
    }

    @Test
    public void chromeVersions() {

        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("117");
        driver = new ChromeDriver(options);
        driver.get("https://www.facebook.com/r.php?locale=en_US");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
