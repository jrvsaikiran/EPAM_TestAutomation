package utiles;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.BaseTestMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Listners implements ITestListener, IAnnotationTransformer {

    public void onTestStart(ITestResult result) {

    }

    public void onTestSuccess(ITestResult result) {

    }
    public void onTestFailure(ITestResult result) {
        File source = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName()+".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            throw new RuntimeException("Unable to take screen shot"+ e.getLocalizedMessage());
        }

    }
    public void onTestSkipped(ITestResult result) {

    }
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }
    public void onStart(ITestContext context) {

    }
    public void onFinish(ITestContext context) {

    }

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(Retry.class);
    }


}
