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
import utiles.Constants;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

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

        fileHandler = new FileHandler("src/main/java/logger.txt");
        logger= Logger.getLogger("java");
        fileHandler.setFormatter(new TextFormatter());
        logger.addHandler(fileHandler);
        logger.info("BeforeTest is started");

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +File.separator+"target"+ File.separator + "reports" + File.separator + "Automation.html");
        System.out.println(System.getProperty("user.dir"));
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
        driver = SingletonPattern.getInstance(browserName);
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
        fileHandler.flush();
        logger.info("AfterTest is started");
        attachLogfileToHtmlReport();
        repotinglog();
        extent.flush();
    }

    public void repotinglog(){
        String filePath = System.getProperty("user.dir") + File.separator + "src"+File.separator+"main"+File.separator+"java"+File.separator+"logger.txt";
            String  fileContentHtml = """
                    <a href= 'Users/rajavenkatasaikiran_/IdeaProjects/TestAutomation/src/main/java/logger.txt'>log file</a>""";
        extentLogs.info(filePath);
        extentLogs.log(Status.INFO, fileContentHtml);
    }

    public void attachLogfileToHtmlReport() {
        String filePath = System.getProperty("user.dir") + File.separator + "src"+File.separator+"main"+File.separator+"java"+File.separator+"logger.txt";
        try {
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            String fileContentHtml = "<pre>" + fileContent + "</pre>";
//            String  fileContentHtml = "<a href='" + fileContent + "'>Download File</a>";
            extentLogs.log(Status.INFO, fileContentHtml);
        } catch (IOException e) {
            e.printStackTrace();
            extentLogs.log(Status.ERROR, "Failed to read file content");
        }
    }
}
