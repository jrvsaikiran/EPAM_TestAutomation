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

    @FindBy(xpath = "(//div[@id='hide_no_result'])[1]")
    WebElement firstPhoto;

    @FindBy(xpath = "//button[@id='nxtlink']")
    WebElement bottomPageNext_btn;

    @FindBy(xpath = "//a[normalize-space()='My Home']/..")
    WebElement home_tab;

    @FindBy(xpath = "//a[normalize-space()='Matches']/..")
    WebElement matches_tab;

    @FindBy(xpath = "//a[starts-with(text(),'Profiles viewed & not contacted')]")
    WebElement profileViewed_NotContacted;

    @FindBy(xpath = "//a[starts-with(text(),'Who viewed my profile ')]")
    WebElement viewedMyProfile;

    @FindBy(xpath = "//a[normalize-space()='Members who might like you']")
    WebElement membersWhoMightLikeYou;

    @FindBy(xpath = "//a[starts-with(text(),'Latest Matches')]")
    WebElement latestMatches;

    @FindBy(xpath = "//a[starts-with(text(),'Yet to be viewed ')]")
    WebElement yetToBeViewed;

    @FindBy(xpath = "//a[starts-with(text(),'Mutual Matches')]")
    WebElement mutualMatches;

    @FindBy(xpath = "//div[@id='pagination']//span[@class='nextactive']")
    WebElement topNext_btn;

    @FindBy(xpath = "//span[@class='nextactive']")
    WebElement bottomNext_btn;

    @FindBy(xpath = "//div[@id='prime_div']//span[@id='prime']")
    WebElement prime_btn;

    public void loginFunction() throws Exception {
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

    public void selectPrime(boolean prime) {
        Actions action = new Actions(driver);
        driver.navigate().refresh();
        try {
            if (prime) {
                pageLoad();
                action.doubleClick(prime_btn).build().perform();
                click(prime_btn);
                pageLoad();
                System.out.println("Switched to PRIME mode");
            }
        } catch (Exception e) {

        }
    }

    public void switchToSpecificTab(String str) throws Exception {
        pageLoad();
        Actions actions = new Actions(driver);
        if(Integer.parseInt(str)<=2){
             pageLoad();
             actions.moveToElement(home_tab).build().perform();
             actions.moveToElement(home_tab).build().perform();
             pageLoad();
         }else{
             pageLoad();
             actions.moveToElement(matches_tab).build().perform();
             actions.moveToElement(matches_tab).build().perform();
             pageLoad();
         }
        switch (str){
            case "1":
                click(viewedMyProfile);
                pageLoad();
                break;
            case "2":
                click(profileViewed_NotContacted);
                pageLoad();
                break;
            case "3":
                click(membersWhoMightLikeYou);
                break;
            case "4":
                click(latestMatches);
                break;
            case "5":
                click(yetToBeViewed);
                break;
            case "6":
                click(mutualMatches);
                break;
            default:
                throw new Exception("mention proper tab number---->>>");

        }

    }

    public void firstPicClick() throws Exception {
        pageLoad();
        click(firstPhoto);
        windowHandle(2);
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

    private void loop(int i) throws Exception {
        Actions actions = new Actions(driver);
        WebElement nextBtn = null;
        pageLoad();
        pageLoad();
        pageLoad();
        try {
             nextBtn = driver.findElement(By.xpath("(//button[@id='nxtproflink'])[" + i + "]"));
            if(nextBtn.isDisplayed()){
                wait(nextBtn);
                actions.moveToElement(nextBtn).moveToElement(nextBtn).build().perform();
                pageLoad();
                Thread.sleep(1000);
                actions.moveToElement(nextBtn).moveToElement(nextBtn).build().perform();
                Thread.sleep(1000);
                click(nextBtn);
                pageLoad();
                pageLoad();

            }

        }
        catch (WebDriverException e) {
            try {
                if(!nextBtn.isDisplayed()){
                    throw new Exception("Next button not displayed "+nextBtn);
                }
            } catch (NullPointerException | WebDriverException n){
                handleBottomNextBtn();
                pageLoad();
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


    private static int nextBtn_inPages = 1;

    private void handleBottomNextBtn() throws Exception {
        driver.close();
        windowHandle(1);
        moveToEle(bottomNext_btn);
        try {
            if (bottomNext_btn.isDisplayed()) {
                moveToEle(bottomNext_btn);
                click(bottomNext_btn);
                System.out.println("Clicked Bottom NEXT button:- " + nextBtn_inPages);
            }
        } catch (NoSuchElementException e){
            throw new Exception("Next button is not displayed");
        }
        catch (Exception e) {
            handleBottomNextBtn();
        }
        nextBtn_inPages++;
    }


    private void click(WebElement element) {

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

    private void windowHandle(int stop) throws Exception {
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
            throw new Exception("unable to handle window");
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
