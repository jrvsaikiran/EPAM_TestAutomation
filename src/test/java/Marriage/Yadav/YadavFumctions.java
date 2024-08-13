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
    private String switchingTab;

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

    @FindBy(xpath = "//span[normalize-space()='viewed your profile']")
    WebElement viewedYourProfile;

    @FindBy(xpath = "//div[@id='pagination']//span[@class='nextactive']")
    WebElement topNext_btn;

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

    public void switchToSpecificTab(String str) {
        driver.navigate().refresh();
        pageLoad();
        Actions actions = new Actions(driver);
         switchingTab = str;
        switch (str){
            case "1":
                try {
                    Thread.sleep(10000);
                    actions.moveToElement(home_tab).build().perform();
                    Thread.sleep(10000);
                    pageLoad();
                    actions.moveToElement(home_tab).build().perform();
                    pageLoad();
                } catch (Exception e) {
                    switchToSpecificTab(str);
                }
                click(viewedMyProfile);
                pageLoad();
                break;
            case "2":
                pageLoad();
                moveToEle(viewedYourProfile);
                click(viewedYourProfile);
                windowHandle(2);
                pageLoad();
        }

    }

    public void firstPicClick() {
        switch (switchingTab){
            case "1":
                pageLoad();
                click(firstPhoto);
                windowHandle(2);
                break;
            case "2":
                pageLoad();
                click(firstPhoto);
                windowHandle(3);
                break;
        }

    }

   private static int iterations = 1;

    public void pageIterations() throws Exception {
        pageLoad();
        pageLoad();
        nextIteration(1);
        if (isBottomPageNextBtnDisplayed()) {
            click(bottomPageNext_btn);
            pageLoad();
            System.out.println(" clicked BOTTOM NEXT button:- "+iterations);
            iterations++;
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            pageLoad();
        }
        pageLoad();
        pageLoad();
        pageIterations();
    }


    //=========================================================================================================================

  private static   int j = 1;
    private void nextIteration(int i) throws Exception {

        do {
            loop(i);
            System.out.println("click count:-" + j++);
            i++;
        } while (!isBottomPageNextBtnDisplayed());
    }

    private boolean isBottomPageNextBtnDisplayed() {
        boolean displayed = false;
        try {
             displayed = bottomPageNext_btn.isDisplayed();
        } catch (Exception e) {
            isBottomPageNextBtnDisplayed();
        }
        return displayed;
    }

    private static int topNext=1;
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
                }
            } catch (NullPointerException | WebDriverException n){
                handleTopNextBtn();
                pageLoad();
                firstPicClick();
                pageIterations();
            }
            loop(i);
        } catch (Error ee) {
            loop(i);
        } catch (Exception e3) {
            loop(i);
        }

    }

    private void handleTopNextBtn() {
        driver.close();
        switch (switchingTab){
            case "1":
                windowHandle(1);
                break;
            case "2":
                windowHandle(2);
                break;
        }

        click(topNext_btn);
        System.out.println("Clicked TOP NEXT button:- "+topNext);
        topNext++;
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

    private void moveToEle(WebElement ele) {
        Actions action = new Actions(driver);
        try {
            action.moveToElement(ele).build().perform();
        } catch (WebDriverException e) {
            action.moveToElement(ele).build().perform();
        } catch (Exception e) {
            action.moveToElement(ele).build().perform();
        }
    }

}