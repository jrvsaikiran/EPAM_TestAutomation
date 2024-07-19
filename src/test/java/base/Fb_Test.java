package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import dataprovider.DataProvide;
import org.testng.annotations.Test;
import pageEvents.LogInPageEvents;
import utiles.ElementFetch;

import java.io.IOException;

import static base.BaseTest.driver;
import static base.BaseTest.logger;

public class Fb_Test {

    @Test(dataProvider = "facebook", dataProviderClass = DataProvide.class,invocationCount = 1)
    public void tryFB(String a,String b) throws IOException {
        System.out.println(a.toString() + " --- " + b.toString());

        LogInPageEvents login = new LogInPageEvents();
        login.enter();

        String pic = new ElementFetch().takeScreenShot(driver);
        System.out.println(pic);

        logger.info("Image ", MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        logger.log(Status.PASS,"yes ",MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());

    }
}
