package Marriage.Bharath;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;


public class BharathTesting {

    @Test
    public void bharath() throws Exception {

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = getChromeOptions();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.bharatmatrimony.com/login/login.php");
        BharathFunctions m = new BharathFunctions(driver);
        m.loginFunction();
        m.mavenParams();

        m.checkImages();
        driver.quit();

    }
    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("127");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--ignore-urlfetcher-cert-requests");
        chromeOptions.addArguments("--disable-renderer-backgrounding");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--no-sandbox");
        return chromeOptions;
    }




}
