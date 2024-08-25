package Marriage.Bharath;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class MatrimonyFunctions  {
    WebDriver driver;
    private String totalRecords;

    public MatrimonyFunctions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[normalize-space()='Existing Member? Login']/..//form[@name='Login']//input[@id='MIDP']")
    WebElement username;

    @FindBy(xpath = "//div[normalize-space()='Existing Member? Login']/..//form[@name='Login']//input[@id='PASSWORD2']")
    WebElement password;

    @FindBy(xpath = "//a[starts-with(text(),'Skip')]")
    WebElement skiptoMyHome;

    @FindBy(xpath = "//ion-button[starts-with(@class,'prime-button')]")
    WebElement primeMatches_btn;

    @FindBy(xpath = "//ion-button[normalize-space()='Explore Prime']")
    WebElement popupExplorePrime_btn;

    @FindBy(xpath = "//ion-label[normalize-space()='Matches']")
    WebElement matches;

    @FindBy(xpath = "//div[normalize-space()='Existing Member? Login']/..//form[@name='Login']//input[@type='submit']")
    WebElement login;

    @FindBy(xpath = "(//a[@class='remove-underline']//ion-img)[1]")
    WebElement firstPic;

    @FindBy(xpath = "//ion-button[@size='small']")
    WebElement nextButton;

    @FindBy(xpath = "//ion-button[@size='small']//following-sibling::ion-button")
    WebElement nextButton2;

    @FindBy(xpath = "//li[@class='pagination-next']//a")
    WebElement bottomNext_btn;

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

    @FindBy(xpath = "//span[normalize-space()='Parent Created Matches']")
    WebElement parentCreatedMatches_Tab;

    @FindBy(xpath = "//span[normalize-space()='Looking For You']")
    WebElement lookForYou_Tab;

    @FindBy(xpath = "//span[@class='ng-star-inserted']//span[contains(text(),'/')]")
    WebElement recordCount;

    public void loginFunction() {
        try {
            username.sendKeys("9440741024");
        } catch (WebDriverException e) {
            driver.navigate().refresh();
            username.sendKeys("9440741024");
        }
        boolean flag = true;
        do {
            try {
                password.sendKeys("9440741024");
                flag = false;
            } catch (ElementNotInteractableException e) {
            }
        }
        while (flag);
        clickProperty(login);
        if(skiptoMyHome.isDisplayed()){
            clickProperty(skiptoMyHome);
        }
        waitProperty(matches);
    }

    public void primeSelected(boolean b) {
        if (b) {
            clickProperty(primeMatches_btn);
            waitProperty(popupExplorePrime_btn);
            clickProperty(popupExplorePrime_btn);
        }
    }
    public void selectTab(String tab) throws Exception {
        waitProperty(matches);
        clickProperty(matches);
        waitProperty(newlyJoined_Tab);
        try {
            clickTab(tab);
        } catch (WebDriverException e) {
            clickTab(tab);
        }
    }

    public void checkImages() throws Exception {

        waitProperty(firstPic);
        selectingPic(1);
        selectWindow(2);
        driver.navigate().refresh();

        try {
            try{
                clickProperty(nextButton2);
            } catch (Exception e) {
                try {
                    clickProperty(nextButton);
                } catch (Throwable ex) {
                    handleCheckImages();
                }
            }
        } catch (Exception e1) {
            handleCheckImages();

        }
        getRecordCount(recordCount);
        fixedLoopToClickNextBtn();
//        getRecordCount(recordCount);
    }
//    ===============================================================================================
    private static int pic = 2;
    private static int handlePics=pic;
    private void handleCheckImages() {
        try {
            driver.close();
            selectWindow(1);
            selectingPic(pic);
            pic++;
//            handlePics = pic;
            selectWindow(2);
            waitProperty(nextButton2);
            nextButton2.isDisplayed();
        } catch (WebDriverException e2) {
            handleCheckImages();
        }
    }

    private void selectingPic(int i) {
        try {
            WebElement element = driver.findElement(By.xpath("(//a[@class='remove-underline']//ion-img)[" + i + "]"));
            moveToEle(element);
            element.click();
        } catch (WebDriverException e) {
            WebElement element = driver.findElement(By.xpath("(//a[@class='remove-underline']//ion-img)[" + i + "]"));
            moveToEle(element);
            element.click();
        }
    }

    private void getRecordCount(WebElement element) {
        String text;
        try {
            waitProperty(element);
            text = element.getText();
        } catch (NoSuchElementException e) {
            driver.navigate().refresh();
            waitProperty(element);
            text = element.getText();
        } catch (WebDriverException e) {
            driver.navigate().refresh();
            waitProperty(element);
            text = element.getText();
        }
        String[] split = text.split("/");
        totalRecords = split[1];
        System.out.println(" total Records :-" + totalRecords + " & record list :- " + text);
    }


    private void clickTab(String ele) throws Exception {
        switch (ele) {
            case "1":
                clickProperty(allMatches_Tab);
                break;
            case "2":
                clickProperty(newlyJoined_Tab);
                break;
            case "3":
                clickProperty(nearbyMatches_Tab);
                break;
            case "4":
                clickProperty(viewedYou_Tab);
                break;
            case "5":
                clickProperty(viewedByYou_Tab);
                break;
            case "6":
                clickProperty(more_Tab);
                waitProperty(mutualMatches_Tab);
                moveToEle(selfCreatedMatches_Tab);
                clickProperty(selfCreatedMatches_Tab);
                break;
            case "7":
                clickProperty(more_Tab);
                waitProperty(mutualMatches_Tab);
                moveToEle(matches_ThatHavePhotos_Tab);
                clickProperty(matches_ThatHavePhotos_Tab);
                break;
            case "8":
                clickProperty(more_Tab);
                waitProperty(mutualMatches_Tab);
                moveToEle(parentCreatedMatches_Tab);
                clickProperty(parentCreatedMatches_Tab);
                break;
            case "9":
                clickProperty(more_Tab);
                waitProperty(mutualMatches_Tab);
                moveToEle(lookForYou_Tab);
                clickProperty(lookForYou_Tab);
                break;
            case "10":
                clickProperty(more_Tab);
                waitProperty(mutualMatches_Tab);
                moveToEle(mutualMatches_Tab);
                clickProperty(mutualMatches_Tab);
                break;
            default:
                throw new Exception("error with ---------->" + ele);
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

    private void clickProperty(WebElement element) {
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



    private static int nextRec = handlePics;
    private static int cliclCount = 2;
    private void fixedLoopToClickNextBtn() throws Exception {
        final int allRec = Integer.parseInt(totalRecords);
        try {
            do {
                waitProperty(nextButton2);
                waitProperty(nextButton2);
                clickProperty(nextButton2);
                pageLoad();
                nextRec++;
                cliclCount++;
                System.out.println("click count " + cliclCount);
            } while (nextButton2.isDisplayed());
        }
        catch (WebDriverException e) {
            driver.navigate().refresh();
            try {
                while(primeMatches_btn.isDisplayed()){
                    pageLoad();
                    closeWindow(2);
                    selectWindow(1);
                    pageLoad();
                    pageLoad();

                    if(bottomNext_btn.isEnabled()){
                        moveToEle(bottomNext_btn);
                        clickProperty(bottomNext_btn);
                        System.out.println("clicked bottom next button");
                    }
                    else {
                       throw new Exception("---->>>>> next button is not displayed");
                    }
                    checkImages();
                }
            } catch (NoSuchElementException ex) {
                fixedLoopToClickNextBtn();
            }

//            fixedLoopToClickNextBtn();

        }
        System.out.println("Completed the task----->");
    }

    private void closeWindow(int closeWin) {
        try {
            int i = 1;
            Set<String> win = driver.getWindowHandles();
            for (String windowHandle : win) {
                if (closeWin == i) {
                    driver.switchTo().window(windowHandle);
                    String currentUrl = driver.getTitle();
                    System.out.println(currentUrl);
                    driver.close();
                    pageLoad();
                    System.out.println("Window number closed :- "+closeWin);
                }
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException("unable to handle window " + e.getLocalizedMessage());
        }
    }

    private void pageLoad() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    private void selectWindow(int stop) {
        try {
            int i = 1;
            Set<String> win = driver.getWindowHandles();
            for (String windowHandle : win) {
                if (stop == i) {
                    driver.switchTo().window(windowHandle);
                    pageLoad();
                    pageLoad();
                    String currentUrl = driver.getTitle();
                    System.out.println(currentUrl);
                }
                i++;
            }
        } catch (Exception e) {
            selectWindow(stop);
//            throw new RuntimeException("unable to handle window " + e.getLocalizedMessage());
        }
    }

    private void waitProperty(WebElement element) {
        try {
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
            fluentWait.ignoring(WebDriverException.class )
                    .pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(9))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (WebDriverException e) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

}
