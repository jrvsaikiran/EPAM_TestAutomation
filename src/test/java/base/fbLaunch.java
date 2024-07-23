package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import dataprovider.DataProvide;
import org.testng.annotations.Test;
import pageEvents.LogInPageEvents;
import utiles.ElementFetch;

import java.io.IOException;

public class fbLaunch extends BaseTest{

    @Test(dataProvider = "facebook", dataProviderClass = DataProvide.class)
    public static void fb_log1(String a, String b) throws Exception {
        System.out.println(a.toString() + " --- " + b.toString());
        LogInPageEvents login = new LogInPageEvents();
        login.enter(a,b);
        String pic = new ElementFetch().takeScreenShot();
        logger.info(nameOfBrowser, MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        logger.log(Status.PASS,nameOfBrowser,MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        Thread.sleep(100);
    }
    @Test(dataProvider = "facebook", dataProviderClass = DataProvide.class)
    public static void fb_log2(String a, String b) throws Exception {
        System.out.println(a.toString() + " --- " + b.toString());
        LogInPageEvents login = new LogInPageEvents();
        login.enter(a,b);
        String pic = new ElementFetch().takeScreenShot();
        logger.info(nameOfBrowser, MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        logger.log(Status.PASS,nameOfBrowser,MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        Thread.sleep(100);
    }
    @Test(dataProvider = "facebook", dataProviderClass = DataProvide.class)
    public static void fb_log3(String a, String b) throws Exception {
        System.out.println(a.toString() + " --- " + b.toString());
        LogInPageEvents login = new LogInPageEvents();
        login.enter(a,b);
        String pic = new ElementFetch().takeScreenShot();
        logger.info(nameOfBrowser, MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        logger.log(Status.PASS,nameOfBrowser,MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        Thread.sleep(100);
    }

}
