package base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import dataprovider.DataProvide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageEvents.LogInPageEvents;
import utiles.Constants;
import utiles.ElementFetch;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest logger;

     public static WebDriver driver;

    @BeforeTest
    public void beforeTest() {
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
    public void beforeMethod(Method method) {
        logger = extent.createTest(method.getName());
        getDriver("chrome");
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test case : " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else {
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test case : " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        }

        SingletonPattern.quit();

    }


    @AfterTest
    public void afterTest() {
        extent.flush();


    }

    public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome", "firefox", "ie", "safari":
                 driver = SingletonPattern.getInstance(browser).driver();
                driver.manage().window().maximize();
                return driver;
        }

        return driver;
    }


    @Test(dataProvider = "facebook", dataProviderClass = DataProvide.class, invocationCount = 1)
    public void facebook(String a, String b) throws IOException {
        System.out.println(a.toString() + " --- " + b.toString());

        LogInPageEvents login = new LogInPageEvents();
        login.enter(a, b);

        String pic = new ElementFetch().takeScreenShot();

        logger.info("Image ", MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        logger.log(Status.PASS, "yes ", MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());

    }


}
