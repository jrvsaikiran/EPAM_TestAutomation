package Marriage.Bharath;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class MatrimonyTesting {
    @Test
    public void dryRun() throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bharatmatrimony.com/login/login.php");
        MatrimonyFunctions m = new MatrimonyFunctions(driver);
        m.loginFunction();
        m.primeSelected(false);
        m.checkImages("1");
        driver.quit();

    }


}
