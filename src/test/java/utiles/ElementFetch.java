package utiles;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementFetch {


    public WebElement getWebElement(String type, String value) {
        switch (type) {
            case "id":
                return BaseTest.driver.findElement(By.id(value));
            case "name":
                return BaseTest.driver.findElement(By.name(value));
            case "link":
                return BaseTest.driver.findElement(By.linkText(value));
            case "xpath":
                return BaseTest.driver.findElement(By.xpath(value));
            case "css":
                return BaseTest.driver.findElement(By.cssSelector(value));
            default:
                return null;
        }

    }


    public List<WebElement> getElements(String type, String value) {
        switch (type) {
            case "id":
                return BaseTest.driver.findElements(By.id(value));
            case "name":
                return BaseTest.driver.findElements(By.name(value));
            case "link":
                return BaseTest.driver.findElements(By.linkText(value));
            case "xpath":
                return BaseTest.driver.findElements(By.xpath(value));
            case "css":
                return BaseTest.driver.findElements(By.cssSelector(value));
            default:
                return null;
        }
    }


}
