package Marriage.Yadav;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class YadavFumctions {
    private WebDriver driver;

    public YadavFumctions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='idEmail']")
    WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='frmsubmit']")
    WebElement login_btn;

    @FindBy(xpath = "//a[normalize-space()='YDV972623']")
    WebElement account_btn;

    @FindBy(xpath = "//a[starts-with(text(),'Skip')]")
    WebElement skip_btn;

    @FindBy(xpath = "//a[starts-with(@style,'cursor') and @class='popupclose']//img")
    WebElement addsClose_btn;

    @FindBy(xpath = "(//div[@id='hide_no_result'])[1]")
    WebElement firstPhoto;

    @FindBy(xpath = "//span[@ng-if='FirstNavImage']//img")
    WebElement next_btn;

    @FindBy(xpath = "//button[@id='nxtlink']")
    WebElement bottomPageNext_btn;

    @FindBy(xpath = "//button[@id='nxtproflink']")
    WebElement SecondPg_next_Btn;

    @FindBy(xpath = "//a[normalize-space()='My Home']/..")
    WebElement home_tab;

    @FindBy(xpath = "//a[starts-with(text(),'Profiles viewed & not contacted')]")
    WebElement mouseOver_click_on_profileViewed;

    @FindBy(xpath = "//a[starts-with(text(),'Who viewed my profile ')]")
    WebElement viewedMyProfile;

    public void loginFunction() {
        pageLoad();
        send(username, "9440741024");
        send(password, "9440741024");
        click(login_btn);
        pageLoad();
        click(account_btn);
        pageLoad();
        try {
            click(skip_btn);
        } catch (TimeoutException e) {
            windowHandle(1);
        }
    }

    public void switchToSpecificTab() {
        driver.navigate().refresh();

        pageLoad();
        Actions actions = new Actions(driver);
        try {
            Thread.sleep(10000);
            actions.moveToElement(home_tab).build().perform();
            Thread.sleep(10000);
            pageLoad();
            actions.moveToElement(home_tab).build().perform();
            pageLoad();
        } catch (Exception e) {
            switchToSpecificTab();
        }
        click(viewedMyProfile);
        pageLoad();
    }

    public void firstPicClick() {
        pageLoad();
        click(firstPhoto);
        windowHandle(2);
    }

    static int iterations = 1;

    public void selectTab() throws Exception {
        firstPageIteration();

        click(bottomPageNext_btn);
        System.out.println("Moved to next page " + iterations++);

    }

    private void firstPageIteration() throws Exception {
//        click(next_btn);
        pageLoad();
        pageLoad();
        nextIteration(1);
//        click(bottomPageNext_btn);
        if (bottomPageNext_btn.isDisplayed()) {
            click(bottomPageNext_btn);
            pageLoad();
            System.out.println(++iterations + " :-completed and moved to next page:- " + iterations++);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            pageLoad();
        }
        pageLoad();
        pageLoad();
        firstPageIteration();
    }


    //=========================================================================================================================


    private void nextIteration(int i) throws Exception {
        int j = 1;
        do {
            loop(i);

            System.out.println("click count:-" + j++);
            i++;
        } while (!bottomPageNext_btn.isDisplayed());
        System.out.println("No Of iterations is " + i);
    }

    private void loop(int i) throws Exception {
        Actions actions = new Actions(driver);
        WebElement nextBtn = null;
        pageLoad();
        pageLoad();
        pageLoad();
        try {
             nextBtn = driver.findElement(By.xpath("(//button[@id='nxtproflink'])[" + i + "]"));
            if(nextBtn.isDisplayed()){
                actions.moveToElement(nextBtn).moveToElement(nextBtn).build().perform();
                click(nextBtn);
                Thread.sleep(5000);
            }

        }
        catch (WebDriverException e) {
            try {
                if(!nextBtn.isDisplayed()){
                    throw new Exception("Next button not displayed "+nextBtn);
                }else {
                    loop(i);
                }
            } catch (NullPointerException n){
                driver.close();
                throw new Exception("Next button not displayed >>>> "+nextBtn);
            }
            loop(i);
        } catch (Error ee) {
            loop(i);
        } catch (Exception e3) {
            loop(i);
        }

    }


    private void click(WebElement element) {
        Actions action = new Actions(driver);
        try {

            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
            fluentWait.ignoring(WebDriverException.class)
                    .pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(9))
                    .until(ExpectedConditions.elementToBeClickable(element)).click();

        } catch (WebDriverException e) {
            driver.navigate().refresh();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Throwable e) {
            element.click();
        }
    }

    private void pageLoad() {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        } catch (Exception e) {
            pageLoad();
        }
    }

    private void wait(WebElement element) {
        try {
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
            fluentWait.ignoring(WebDriverException.class)
                    .pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(9))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (WebDriverException e) {
            driver.navigate().refresh();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    private void send(WebElement element, String str) {
        try {
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
            fluentWait.ignoring(WebDriverException.class)
                    .pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(9))
                    .until(ExpectedConditions.elementToBeClickable(element)).sendKeys(str);
        } catch (WebDriverException e) {
            driver.navigate().refresh();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(str);
        }
    }

    private void windowHandle(int stop) {
        try {
            int i = 1;
            Set<String> win = driver.getWindowHandles();
            for (String windowHandle : win) {
                if (stop == i) {
                    driver.switchTo().window(windowHandle);
                    String currentUrl = driver.getTitle();
                    System.out.println(currentUrl);
                }
                i++;
            }
        } catch (Exception e) {
            windowHandle(stop);
        }
    }


}
