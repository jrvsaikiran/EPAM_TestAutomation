package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class MatriTest extends BaseTest {
    WebDriver driver;

    public MatriTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Login'][1]")
    WebElement loginButton;

    @FindBy(xpath = "//div[normalize-space()='Existing Member? Login']/..//form[@name='Login']//input[@id='MIDP']")
    WebElement username;

    @FindBy(xpath = "//div[normalize-space()='Existing Member? Login']/..//form[@name='Login']//input[@id='PASSWORD2']")
    WebElement password;

    @FindBy(xpath = "//ion-label[normalize-space()='Matches']")
    WebElement matches;

    @FindBy(xpath = "//div[normalize-space()='Existing Member? Login']/..//form[@name='Login']//input[@type='submit']")
    WebElement login;

    @FindBy(xpath = "(//a[@class='remove-underline']//ion-img)[1]")
    WebElement clickFirstPic;

    @FindBy(xpath = "//ion-button[@size='small']")
    WebElement nextButton;

    @FindBy(xpath = "//ion-button[@size='small']//following-sibling::ion-button")
    WebElement nextButton2;

    @FindBy(xpath = "//ion-button[normalize-space()='All Matches']")
    WebElement allMatches_Tab;

    @FindBy(xpath = "(//ion-button[normalize-space()='Newly Joined'])")
    WebElement newlyJoined_Tab;

    @FindBy(xpath = "//ion-button[normalize-space()='Nearby Matches']")
    WebElement nearbyMatches_Tab;

    @FindBy(xpath = "//ion-button[normalize-space()='Viewed You']")
    WebElement viewedYou_Tab;

    @FindBy(xpath = "//ion-button[normalize-space()='Viewed By You']")
    WebElement viewedByYou_Tab;

    @FindBy(xpath = "//span[normalize-space()='Self Created Matches']")
    WebElement selfCreatedMatches_Tab;

    @FindBy(xpath = "//span[normalize-space()='Matches That Have Photos']")
    WebElement matches_ThatHavePhotos_Tab;

    @FindBy(xpath = "//ion-button[normalize-space()='More']")
    WebElement more_Tab;

    @FindBy(xpath = "//span[normalize-space()='Mutual Matches']")
    WebElement mutualMatches_Tab;


    public void clickTab(String ele) throws Exception {
        Actions action = new Actions(driver);
        switch (ele) {
            case "All Matches":
                allMatches_Tab.click();
                break;
            case "Newly Joined":
                newlyJoined_Tab.click();
                break;
            case "Nearby Matches":
                nearbyMatches_Tab.click();
                break;
            case "Viewed You":
                try {
                    viewedYou_Tab.click();
                } catch (WebDriverException e) {
                    viewedYou_Tab.click();
                }
                break;
            case "Viewed By You":
                viewedByYou_Tab.click();
                break;
            case "Self Created Matches":
                more_Tab.click();
                waitProperty(mutualMatches_Tab);
                action.moveToElement(selfCreatedMatches_Tab).build().perform();
                selfCreatedMatches_Tab.click();
                break;
            case "Matches That Have Photos":
                more_Tab.click();
                waitProperty(mutualMatches_Tab);
                action.moveToElement(matches_ThatHavePhotos_Tab).build().perform();
                matches_ThatHavePhotos_Tab.click();
                break;
            default:
                throw new Exception("error with ---------->"+ele);

        }
    }

    public void loginFunction() {
        username.sendKeys("9440741024");
        boolean flag = true;
        do {
            try {
                password.sendKeys("9440741024");
                flag = false;
            } catch (ElementNotInteractableException e) {
            }
        }
        while (flag);
        login.click();
        waitProperty(matches);


//======================


        List<WebElement> elements = driver.findElements(By.xpath("//ion-img[@alt='profile pic']/.."));
        for (WebElement element : elements) {
            String href = element.getAttribute("href");
            System.out.println(href);
        }
    }

    public void checkImages() throws Exception {
        waitProperty(matches);
        matches.click();
        waitProperty(newlyJoined_Tab);
        clickTab("Viewed You");

        waitProperty(clickFirstPic);
        clickFirstPic.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            String currentUrl = driver.getTitle();
            System.out.println(currentUrl);
        }

        driver.navigate().refresh();

        try {
            waitProperty(nextButton);
            nextButton.click();
        } catch (Throwable e) {
            Set<String> win = driver.getWindowHandles();
            for (String windowHandle : win) {
                driver.switchTo().window(windowHandle);
                String currentUrl = driver.getTitle();
                System.out.println(currentUrl);
                break;
            }

            try {
                clickFirstPic.click();
            } catch (ElementNotInteractableException ex) {
                clickFirstPic.click();

            }

            int i=0;
            Set<String> win2 = driver.getWindowHandles();
            for (String windowHandle : win2) {

                if(i==2){
                    driver.switchTo().window(windowHandle);
                    String currentUrl = driver.getTitle();
                    System.out.println(currentUrl);
                }
                i++;

            }
            try {
                waitProperty(nextButton);
            } catch (TimeoutException ex) {
                waitProperty(nextButton);
            }
            nextButton.click();
        }

        boolean flag = false;
        do {
            int i = 0;
            do {
                try {
                    waitProperty(nextButton2);
                    nextButton2.click();
                    i++;
                    waitProperty(nextButton2);
                    flag = true;
                } catch (StaleElementReferenceException ignored) {
                }
            } while (i < 5);
        } while (flag);
    }

    public void waitProperty(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
