package pageEvents;

import base.BaseTest;
import dataprovider.DataProvide;
import pageobjects.LogInPageEle;
import utiles.ElementFetch;

public class LogInPageEvents  {

    public static void enter(String a, String b) throws Exception {
        ElementFetch fetch = new ElementFetch();
            fetch.getWebElement("xpath", LogInPageEle.email_xpath).sendKeys(a);
            fetch.getWebElement("xpath", LogInPageEle.password_xpath).sendKeys(b);

    }

}
