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
        ChromeOptions chromeOptions = getChromeOptions();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.yadavmatrimony.com/login/logout.php");
        YadavFumctions test = new YadavFumctions(driver);
        test.loginFunction();
        test.switchToSpecificTab("2");
        test.firstPicClick();
        test.pageIterations();
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
