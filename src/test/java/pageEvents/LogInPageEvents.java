package pageEvents;

import base.BaseTest;
import dataprovider.DataProvide;
import pageobjects.LogInPageEle;
import utiles.ElementFetch;

public class LogInPageEvents extends BaseTest {


    public LogInPageEvents() {
        super();
    }

    public void enter() {
        ElementFetch fetch = new ElementFetch();
        for (String[] strings : DataProvide.getList) {
            fetch.getWebElement("xpath", LogInPageEle.email_xpath).sendKeys(strings[0]);
            fetch.getWebElement("xpath", LogInPageEle.password_xpath).sendKeys(strings[1]);
        }


    }
}
