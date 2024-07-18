package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utiles.Constants;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public BaseTest(WebDriver driver){
        this.driver = SingletonPattern.getInstance().method();

    }

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest logger;

public  static WebDriver driver = SingletonPattern.getInstance().method();

    @BeforeTest
    public void setUp() {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Automation.html");
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Jrv Sai Kiran");

    }

    @BeforeMethod
    @Parameters({"browserName"})
    public void tearDown(String browser, Method method) {
        logger = extent.createTest(method.getName());
        getDriver("chrome");
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);



    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test case : " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
            logger.log(Status.PASS,m);
        }else {
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test case : " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
            logger.log(Status.FAIL,m);
        }

        driver.quit();

    }


    @AfterTest
    public void tearDownClass() {
        extent.flush();


    }

    public static WebDriver getDriver(String str) {


        switch (str.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                return driver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return driver;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                return driver;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                return driver;
        }
        driver.manage().window().maximize();
        return driver;
    }


}
