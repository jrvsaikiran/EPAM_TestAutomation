package utiles;

import base.BaseTest;
import base.SingletonPattern;
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
import java.util.Random;



public class Listners extends BaseTest implements ITestListener, IAnnotationTransformer {


    public Listners(WebDriver driver) {
        super(driver);
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Listners onTestStart");

    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Listners onTestSuccess");

    }
    public void onTestFailure(ITestResult result) {
        Random random = new Random();
        int i = random.nextInt();
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName()+i+".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            throw new RuntimeException("Unable to take screen shot"+ e.getLocalizedMessage());
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
