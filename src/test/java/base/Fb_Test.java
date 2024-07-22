package base;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import dataprovider.DataProvide;
import org.testng.annotations.Test;
import pageEvents.LogInPageEvents;
import utiles.ElementFetch;

import java.io.IOException;

public class Fb_Test extends BaseTest{

    @Test(dataProvider = "facebook", dataProviderClass = DataProvide.class)
    public static void chrome(String a, String b) throws IOException {
        System.out.println(a.toString() + " --- " + b.toString());

        LogInPageEvents login = new LogInPageEvents();
        login.enter(a,b);

        String pic = new ElementFetch().takeScreenShot();
        logger.info("chrome ", MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());
        logger.log(Status.PASS,"chrome ",MediaEntityBuilder.createScreenCaptureFromBase64String(pic).build());

    }

}
