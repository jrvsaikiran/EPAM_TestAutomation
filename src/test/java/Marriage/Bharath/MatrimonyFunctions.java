package Marriage.Bharath;

import Marriage.pdfExcelImg.Convert_Image_To_PDF;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MatrimonyFunctions  {
    WebDriver driver;
    private String totalRecords;
    private  String startingRecordCount;

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

    @FindBy(xpath = "//ion-col[starts-with(@class,'profile-name')]")
    WebElement name;

    @FindBy(xpath = "((//ion-col[starts-with(@class,'profile-name')]/../..//ion-row)[3]/ion-col)[1]")
    WebElement age;

    @FindBy(xpath = "((//ion-col[starts-with(@class,'profile-name')]/../..//ion-row)[3]/ion-col)[2]")
    WebElement cast;

    @FindBy(xpath = "((//ion-col[starts-with(@class,'profile-name')]/../..//ion-row)[3]/ion-col)[3]")
    WebElement education;

    @FindBy(xpath = "((//ion-col[starts-with(@class,'profile-name')]/../..//ion-row)[3]/ion-col)[4]")
    WebElement location;

    @FindBy(xpath = "//ion-col[starts-with(@class,'matriid-lastlogin')]")
    WebElement activity;

    @FindBy(xpath = "//ion-row[@class='ion-padding viewprofile-card md hydrated']")
    WebElement profilePic;

    public void loginFunction() {
        try {
            username.sendKeys("9440741024");
        } catch (WebDriverException e) {
            refreshProperty();
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
        try {
            if(skiptoMyHome.isDisplayed()){
                clickProperty(skiptoMyHome);
            }
        } catch (Exception e) {
            pageLoad();
        }
        waitProperty(matches);
    }

    private void refreshProperty() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            pageLoad();
            pageLoad();
        }
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
        refreshProperty();

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
    }
//    ===============================================================================================
    private static int pic = 2;
    private void handleCheckImages() {
        try {
            closeWindow(2);
            selectWindow(1);
            selectingPic(pic);
            pic++;
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
        } catch (WebDriverException e) {
            refreshProperty();
            waitProperty(element);
            text = element.getText();
        }
        String[] split = text.split("/");
         startingRecordCount = split[0];
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
            refreshProperty();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Throwable e) {
            element.click();
        }
    }

    private static int startingCount;
    private void fixedLoopToClickNextBtn() throws Exception {
        final int allRec = Integer.parseInt(totalRecords);
         startingCount = Integer.parseInt(startingRecordCount);
        try {
            do {
                waitProperty(nextButton2);
                waitProperty(nextButton2);
                readDataToExcel();  //excel reader
                clickProperty(nextButton2);
                pageLoad();
                System.out.println("clicked account " +startingCount);
                Thread.sleep(100);
                startingCount++;
                if(!(allRec >startingCount)){
                    getRecordCount(recordCount);
                }
            } while (allRec>startingCount);
        }
        catch (WebDriverException e) {
            try {
                refreshProperty();
            } catch (Exception ex) {
               pageLoad();
               pageLoad();
            }
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
        }
        System.out.println("Completed the task----->");
    }

    private void readDataToExcel() {

        String nameTxt = getEleText(name);
        String ageTxt = getEleText(age);
        String castTxt = getEleText(cast);
        String educationTxt = getEleText(education);
        String locationTxt = getEleText(location);
        String activityTxt = getEleText(activity);

        screenShots(nameTxt);

        List<ProfileData> list = new LinkedList<>();
        list.add(new ProfileData(nameTxt,ageTxt,castTxt,educationTxt,locationTxt,activityTxt));
//        System.out.println(list);
        Dp_data dp = new Dp_data();
        dp.readBharathData(list, destinationOfImg_png);

    }

    private String destinationOfImg_png;
   private String name_date_ofImg;
    private void screenShots(String nameTxt) {
        String dtTim;
        try {
             dtTim = getCurrentTimeDate();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        File source = profilePic.getScreenshotAs(OutputType.FILE);
        try {
            File destination = new File("C:/Users/rajavenkatasaikiran_/IdeaProjects/TestAutomation/src/test/java/Marriage/Bharath/Screenshots/"+ nameTxt +dtTim+".png");
             destinationOfImg_png = String.valueOf(destination);
             name_date_ofImg = nameTxt.replace(" ","");
            FileUtils.copyFile(source,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Convert_Image_To_PDF pdf = new Convert_Image_To_PDF();
            pdf.createPdfs(destinationOfImg_png, name_date_ofImg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getCurrentTimeDate() {
        LocalDate date = LocalDate.now();
        String s1 = String.valueOf(date);

        LocalTime time = LocalTime.now();
        String s = String.valueOf(time);
        String[] split = s.split("\\.");
        String s2 = split[0].replace(":", "-");

        String dtTim = "_"+s1 +"_"+ s2;
        return dtTim;
    }

    private String getEleText(WebElement ele) {
        String text = "";
        try {
             text = ele.getText();
        } catch (Exception e) {
            try {
                refreshProperty();
                pageLoad();
                text = ele.getText();
            } catch (Exception ex) {
                text = ele.getText();
            }
        }
        return text;
    }

    private void closeWindow(int closeWin) {
        try {
            int i = 1;
            Set<String> win = driver.getWindowHandles();
            for (String windowHandle : win) {
                if (closeWin == i) {
                    driver.switchTo().window(windowHandle);
                    String currentUrl = driver.getTitle();
                    driver.close();
                    pageLoad();
                    System.out.println("driver closed >>" + closeWin + "<< window title is:- "+currentUrl);
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
                    System.out.println("driver switched to >>" + stop + "<< window title is:- " + driver.getTitle());
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
