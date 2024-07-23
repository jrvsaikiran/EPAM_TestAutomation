package base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.log.TextFormatter;
import pageEvents.LogInPageEvents;
import utiles.Constants;
import utiles.ElementFetch;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class BaseTest {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest extentLogs;
    public static Logger logger;
    public static FileHandler fileHandler;
    public static WebDriver driver;
    public static String nameOfBrowser;

    @BeforeTest
    public void beforeTest() throws IOException {

        fileHandler = new FileHandler("src/main/java/log.file");
        logger= Logger.getLogger("java");
        fileHandler.setFormatter(new TextFormatter());
        logger.addHandler(fileHandler);
        logger.info("BeforeTest is started");

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Automation.html");
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Jrv Sai Kiran");

    }

    @Parameters("browserName")
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(String browserName,Method method) {
        logger.info("BeforeMethod is started");
        extentLogs = extent.createTest(method.getName());
        nameOfBrowser = browserName;
        driver = SingletonPattern.getInstance(browserName).driver();
        driver.manage().window().maximize();
        driver.get(Constants.url);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) throws IOException {
        logger.info("AfterMethod is started");
        ITestResult iTestResult = result;
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test case : " + methodName + " Passed";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
            extentLogs.log(Status.PASS, m);
        } else {
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test case : " + methodName + " Failed";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
            extentLogs.log(Status.FAIL, m);
        }
        SingletonPattern.quit();
    }


    @AfterTest
    public void afterTest() {
        logger.info("AfterTest is started");
        fileHandler.flush();
        extent.flush();

    }

}
