package base;

import org.testng.annotations.Test;
import pageEvents.LogInPageEvents;

import static base.BaseTest.driver;


public class Test_POM {


    @Test
    public void test() throws InterruptedException {

        new LogInPageEvents(driver).enter();

    }
}
