package pageEvents;

import base.BaseTest;
import base.SingletonPattern;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pageobjects.LogInPageEle;
import utiles.ElementFetch;

public class LogInPageEvents extends BaseTest {


    public LogInPageEvents(WebDriver driver) {
        super(driver);
    }

    public void enter() {
        ElementFetch fetch = new ElementFetch(driver);
        fetch.getWebElement("xpath", LogInPageEle.email_xpath).sendKeys("sai@edd");
        fetch.getWebElement("xpath", LogInPageEle.password_xpath).sendKeys("dfewfefrf");
    }
}
