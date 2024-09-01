package Marriage.Yadav;

import Marriage.Bharath.MatrimonyFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        if(System.getProperty("jenkins").equalsIgnoreCase("true")){
            test.selectPrime(Boolean.valueOf(System.getProperty("prime")));
            test.switchToSpecificTab(System.getProperty("tab"));
        }else {
            test.selectPrime(false);
            test.switchToSpecificTab("6");
        }

        test.firstPicClick();
        test.pageIterations();
        driver.quit();
    }

    @Test
    public void mixed() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = getChromeOptions();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.bharatmatrimony.com/login/login.php");
        MatrimonyFunctions m = new MatrimonyFunctions(driver);
        m.loginFunction();
         m.mixedProfiles();
        YadavFumctions test = new YadavFumctions(driver);
        if(System.getProperty("jenkins").equalsIgnoreCase("true")){
            test.selectPrime(Boolean.valueOf(System.getProperty("prime")));
            test.switchToSpecificTab(System.getProperty("tab"));
        }else {
            test.selectPrime(false);
            test.switchToSpecificTab("6");
        }
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
