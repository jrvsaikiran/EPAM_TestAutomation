package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Matrimoni {
    @org.testng.annotations.Test
    public void dryRun() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bharatmatrimony.com/login/login.php");
        MatriTest m = new MatriTest(driver);
        m.loginFunction();
        m.checkImages();
    }


}
