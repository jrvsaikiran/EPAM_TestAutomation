package Marriage.Bharath;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

@Data
public class MatrimonyTesting {
    private String jenkins;
    private String prime ;
    private String tab;
    private String testngXml;



    @Test
    public void dryRun() throws Exception {

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = getChromeOptions();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.bharatmatrimony.com/login/login.php");
        MatrimonyFunctions m = new MatrimonyFunctions(driver);
        m.loginFunction();
        getParameters();
        if (getJenkins().equalsIgnoreCase("true")) {
            m.primeSelected(Boolean.valueOf(getPrime()));
            m.selectTab(getTab());
        }else {
            m.primeSelected(true);
            m.selectTab("3");
        }
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

    public void getParameters(){
        setJenkins(System.getProperty("jenkins"));
        setPrime(System.getProperty("prime"));
        setTab(System.getProperty("tab"));
        setTestngXml(System.getProperty("testng.xml"));


        System.out.println("Tab -------->"+getTab());
        System.out.println("Prime ------>"+getPrime());
        System.out.println("Jenkins ---->"+getJenkins());
        System.out.println("Testng.xml ->"+ getTestngXml());
    }


}
