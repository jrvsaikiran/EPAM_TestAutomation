package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class MatrimonyFunctions extends BaseTest {
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
        click(login);
        wait(matches);
    }

    public void checkImages(String tabNumber) throws Exception {
        selectTab(tabNumber);
        wait(firstPic);
        selectingPic(1);
        windowHandle(2);
        driver.navigate().refresh();

        try {
            wait(nextButton);
            click(nextButton);
        } catch (WebDriverException e1) {
            int j = 3;
            for (int i=2; i<10; i++) {
//                if(!nextButton2.isDisplayed()){
                    windowHandle(1);
                    selectingPic(i);
                    windowHandle(j);
                    wait(nextButton2);
                    click(nextButton2);
                    j++;
//                }


            }
//            try {
//                windowHandle(1);
//                selectingPic(2);
//                windowHandle(3);
//                wait(nextButton2);
//                click(nextButton2);
//            } catch (WebDriverException e2) {
//                try {
//                    windowHandle(1);
//                    selectingPic(3);
//                    windowHandle(4);
//                    wait(nextButton2);
//                    click(nextButton2);
//                }catch (WebDriverException e3){
//                    try {
//                        windowHandle(1);
//                        selectingPic(4);
//                        windowHandle(5);
//                        wait(nextButton2);
//                        click(nextButton2);
//                    }catch (WebDriverException e4){
//                        try {
//                            windowHandle(1);
//                            selectingPic(5);
//                            windowHandle(6);
//                            wait(nextButton2);
//                            click(nextButton2);
//                        }catch (WebDriverException e5){
//                            throw new Exception("next button unavailable----->"+e5.getMessage());
//                        }
//                    }
//                }
//            }
        }
        getRecordCount(recordCount);
        fixedLoopToClickNextBtn();
        getRecordCount(recordCount);
    }

    private void selectingPic(int i)  {
        try {
            WebElement element = driver.findElement(By.xpath("(//a[@class='remove-underline']//ion-img)["+i+"]"));
            moveToEle(element);
            element.click();
        } catch (WebDriverException e) {
            WebElement element = driver.findElement(By.xpath("(//a[@class='remove-underline']//ion-img)["+i+"]"));
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
        }catch (TimeoutException e){
            driver.navigate().refresh();
            wait(element);
            text = element.getText();
        }
        String[] split = text.split("/");
         totalRecords = split[1];
        System.out.println(" totalRecords :-"+totalRecords+" & recordCount_Tab :- "+text);
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

    public void primeSelected(boolean b) {
        if (b) {
            click(primeMatches_btn);
            wait(popupExplorePrime_btn);
            click(popupExplorePrime_btn);
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

    private void selectTab(String tab) throws Exception {
        wait(matches);
        click(matches);
        wait(newlyJoined_Tab);
        try {
            clickTab(tab);
        } catch (WebDriverException e) {
            clickTab(tab);
        }
    }

    private void fixedLoopToClickNextBtn() {

       int l=2;
        int allRec = Integer.parseInt(totalRecords);

        try {
            do{
                wait(nextButton2);
                wait(nextButton2);
                click(nextButton2);
                l++;
            }while (allRec>l);
        } catch (WebDriverException e) {
           driver.navigate().refresh();
            do{
                wait(nextButton2);
                wait(nextButton2);
                click(nextButton2);
                l++;
            }while (allRec>l);
        }
        System.out.println("Completed the task----->");
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
            throw new RuntimeException("unable to handle window " + e.getLocalizedMessage());
        }
    }

    private void wait(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (WebDriverException e) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

}
