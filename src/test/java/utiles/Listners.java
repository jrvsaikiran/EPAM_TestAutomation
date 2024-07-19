package utiles;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Random;

import static base.BaseTest.driver;
import static base.BaseTest.logger;


public class Listners implements ITestListener, IAnnotationTransformer {

    public void onTestStart(ITestResult result) {
        System.out.println("Listners onTestStart");

    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Listners onTestSuccess");

    }
    public void onTestFailure(ITestResult result) {
        Random random = new Random();
        int i = random.nextInt();
        ElementFetch fetch = new ElementFetch();
        String pic = fetch.takeScreenShot(driver);
        try {
            logger.info(result.getMethod().getMethodName()+" Failed case ",MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void onTestSkipped(ITestResult result) {
        System.out.println("Listners onTestSkipped");

    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Listners onTestFailedButWithinSuccessPercentage");

    }
    public void onStart(ITestContext context) {
        System.out.println("Listners onStart");


    }
    public void onFinish(ITestContext context) {
        System.out.println("Listners onFinish");

    }

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(Retry.class);
    }


}
