package Marriage.Yadav;

import Marriage.Bharath.MatrimonyFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class YadavTest {

    @Test
    public void yadavMarriage() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
//        chromeOptions.setAcceptInsecureCerts(true);
//        chromeOptions.addArguments("--disable-web-security");
//        chromeOptions.addArguments("--ignore-urlfetcher-cert-requests");
//        chromeOptions.addArguments("--disable-renderer-backgrounding");
//        chromeOptions.addArguments("--disable-infobars");
//        chromeOptions.addArguments("--headless");
//        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//        chromeOptions.addArguments("--no-sandbox"); // Bypass 0S security model
//        chromeOptions.addArguments("window-size=1200,800");
//        chromeOptions.addArguments("force-device-scale-factor=0.85");
//        chromeOptions.addArguments("high-dpi-support=0.85");
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.yadavmatrimony.com/login/logout.php");
        YadavFumctions test = new YadavFumctions(driver);
        test.loginFunction();
        test.selectTab();

//        driver.quit();

    }
}
