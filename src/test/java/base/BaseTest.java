package base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import dataprovider.DataProvide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobjects.LogInPageEle;
import utiles.Constants;
import utiles.ElementFetch;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public BaseTest(){
          }

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public ITestResult result;

public  static WebDriver driver;

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
    public void tearDown( Method method) {
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
                driver.manage().window().maximize();
                return driver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                return driver;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                return driver;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                driver.manage().window().maximize();
                return driver;
        }

        return driver;
    }


    @Test(dataProvider = "facebook", dataProviderClass = DataProvide.class,invocationCount = 1)
    public void facebook(String a, String b) throws IOException {
        System.out.println(a.toString() + " --- " + b.toString());

        ElementFetch fetch = new ElementFetch();
        fetch.getWebElement("xpath", LogInPageEle.email_xpath,driver).sendKeys(a);
        fetch.getWebElement("xpath", LogInPageEle.password_xpath,driver).sendKeys(b);
        String pic = fetch.takeScreenShot(driver);
        System.out.println(pic);

        logger.info("Image ",MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());

        Assert.assertEquals(3,2);

        for (String[] strings : DataProvide.getList) {
            for (String string : strings) {
                System.out.println(string);
            }

        }
    }


}
