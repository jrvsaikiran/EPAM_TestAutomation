package utiles;

import base.BaseTest;
import base.SingletonPattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementFetch extends BaseTest {


    public ElementFetch(WebDriver driver) {
        super(driver);
    }

    public WebElement getWebElement(String type, String value) {

        switch (type) {
            case "id":
                return driver.findElement(By.id(value));
            case "name":
                return driver.findElement(By.name(value));
            case "link":
                return driver.findElement(By.linkText(value));
            case "xpath":
                return driver.findElement(By.xpath(value));
            case "css":
                return driver.findElement(By.cssSelector(value));
            default:
                return null;
        }

    }


    public List<WebElement> getElements(String type, String value) {
        switch (type) {
            case "id":
                return driver.findElements(By.id(value));
            case "name":
                return driver.findElements(By.name(value));
            case "link":
                return driver.findElements(By.linkText(value));
            case "xpath":
                return driver.findElements(By.xpath(value));
            case "css":
                return driver.findElements(By.cssSelector(value));
            default:
                return null;
        }
    }


}
