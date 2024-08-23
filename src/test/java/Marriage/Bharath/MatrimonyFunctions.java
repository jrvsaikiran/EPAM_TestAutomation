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

    @FindBy(xpath = "//ion-button[starts-with(@class,'prime-button md')]")
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
        click(login);
        wait(matches);
    }

    public void primeSelected(boolean b) {
        if (b) {
            click(primeMatches_btn);
            wait(popupExplorePrime_btn);
            click(popupExplorePrime_btn);
        }
    }
    public void selectTab(String tab) throws Exception {
        wait(matches);
        click(matches);
        wait(newlyJoined_Tab);
        try {
            clickTab(tab);
        } catch (WebDriverException e) {
            clickTab(tab);
        }
    }

    public void checkImages() {

        wait(firstPic);
        selectingPic(1);
        selectWindow(2);
        driver.navigate().refresh();

        try {
            wait(nextButton);
            click(nextButton);
        } catch (WebDriverException e1) {
            handleCheckImages();

        }
        getRecordCount(recordCount);
        fixedLoopToClickNextBtn();
        getRecordCount(recordCount);
    }
//    ===============================================================================================
    private static int pic = 2;
    private void handleCheckImages() {
        try {
            driver.close();
            selectWindow(1);
            selectingPic(pic);
            pic++;
            selectWindow(2);
            wait(nextButton2);
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
            wait(element);
            text = element.getText();
        } catch (NoSuchElementException e) {
            driver.navigate().refresh();
            wait(element);
            text = element.getText();
        } catch (WebDriverException e) {
            driver.navigate().refresh();
            wait(element);
            text = element.getText();
        }
        String[] split = text.split("/");
        totalRecords = split[1];
        System.out.println(" total Records :-" + totalRecords + " & record list :- " + text);
    }


    private void clickTab(String ele) throws Exception {
        switch (ele) {
            case "1":
                click(allMatches_Tab);
                break;
            case "2":
                click(newlyJoined_Tab);
                break;
            case "3":
                click(nearbyMatches_Tab);
                break;
            case "4":
                click(viewedYou_Tab);
                break;
            case "5":
                click(viewedByYou_Tab);
                break;
            case "6":
                click(more_Tab);
                wait(mutualMatches_Tab);
                moveToEle(selfCreatedMatches_Tab);
                click(selfCreatedMatches_Tab);
                break;
            case "7":
                click(more_Tab);
                wait(mutualMatches_Tab);
                moveToEle(matches_ThatHavePhotos_Tab);
                click(matches_ThatHavePhotos_Tab);
                break;
            case "8":
                click(more_Tab);
                wait(mutualMatches_Tab);
                moveToEle(parentCreatedMatches_Tab);
                click(parentCreatedMatches_Tab);
                break;
            case "9":
                click(more_Tab);
                wait(mutualMatches_Tab);
                moveToEle(lookForYou_Tab);
                click(lookForYou_Tab);
                break;
            case "10":
                click(more_Tab);
                wait(mutualMatches_Tab);
                moveToEle(mutualMatches_Tab);
                click(mutualMatches_Tab);
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





    private void click(WebElement element) {
        try {
            element.click();
        } catch (WebDriverException e) {
            element.click();
        } catch (Throwable e) {
            element.click();
        }
    }



    private static int nextRec = pic;
    private static int cliclCount = 2;
    private void fixedLoopToClickNextBtn() {
        final int allRec = Integer.parseInt(totalRecords);
        try {
            do {
                wait(nextButton2);
                wait(nextButton2);
                click(nextButton2);
                pageLoad();
                nextRec++;
                cliclCount++;
                System.out.println("click count "+cliclCount);
            } while (allRec > nextRec);
        } catch (WebDriverException e) {
            driver.navigate().refresh();
            try {
                while(allMatches_Tab.isDisplayed()){
                    pageLoad();
                    closeWindow(2);
                    selectWindow(1);
                    pageLoad();
                    pageLoad();
                    moveToEle(bottomNext_btn);
                    click(bottomNext_btn);
                    checkImages();
                }
            } catch (Exception ex) {
                fixedLoopToClickNextBtn();
            }


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

    private void wait(WebElement element) {
        try {
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
            fluentWait.ignoring(WebDriverException.class )
                    .pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(9))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (WebDriverException e) {
            driver.navigate().refresh();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

}
