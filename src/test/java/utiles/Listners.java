package utiles;

import base.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static base.BaseTest.extentLogs;
import static base.BaseTest.logger;


public class Listners extends BaseTest implements ITestListener, IAnnotationTransformer {

    public void onTestStart(ITestResult result) {
        System.out.println("Listners onTestStart");
        logger.info("Listners onTestStart");

    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Listners onTestSuccess");
        logger.info("Listners onTestSuccess");

    }
    public void onTestFailure(ITestResult result) {
        logger.info("Listners onTestFailure");
        ElementFetch fetch = new ElementFetch();
        String pic = fetch.takeScreenShot();
        try {
            extentLogs.info(result.getMethod().getMethodName()+" Failed case ",MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void onTestSkipped(ITestResult result) {
        System.out.println("Listners onTestSkipped");
        logger.info("Listners onTestSkipped");

    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Listners onTestFailedButWithinSuccessPercentage");
        logger.info("Listners onTestFailedButWithinSuccessPercentage");

    }
    public void onStart(ITestContext context) {
        System.out.println("Listners onStart");
    }

    public void onFinish(ITestContext context) {
        System.out.println("Listners onFinish");
        logger.info("Listners onFinish");

    }

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(Retry.class);
       System.out.println("Listners transform");
    }


}
