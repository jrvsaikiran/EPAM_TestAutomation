package Marriage.Bharath;

import Marriage.pdfExcelImg.Convert_Image_To_PDF;
import Marriage.pdfExcelImg.ExcelToHtml;
import Marriage.pdfExcelImg.Parameters;
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

public class BharathFunctions {
    private static WebDriver driver;
    protected static String totalRecords;
    private static int startingRecordCount;

    public BharathFunctions(WebDriver driver) {
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

    @FindBy(xpath = "((//a[@class='remove-underline']//ion-img)/ancestor::app-match-card)[1]/../following-sibling::div[@class='paginate']//li[@class='pagination-next']/a")
    WebElement NEXT_BTN_UnderPage;

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

    @FindBy(xpath = "//*[contains(text(),'Exclusive matches from TechieMatrimony')]/../../..//following-sibling::ion-row//following-sibling::ion-col//ion-button")
    public WebElement Expolre_matches_yadav_Profile;

    public void loginFunction() {
        try {
            username.sendKeys("8985532589");
        } catch (WebDriverException e) {
            refreshProperty();
            username.sendKeys("8985532589");
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

            try{
                clickProperty(nextButton2);
                try {
                    clickProperty(nextButton);
                } catch (Exception exc) {
                    clickProperty(nextButton2);
                }
            } catch (Exception e) {
                try {
                    clickProperty(nextButton);
                } catch (Exception ex) {
                    handleCheckImages();
                }
            }


        if (totalRecords==null) {
            getRecordCount(recordCount);
        }
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
        } catch (Exception e2) {
            handleCheckImages();
        }
    }

    private void selectingPic(int i) throws Exception {
        WebElement element;
        try {
            element = driver.findElement(By.xpath("(//a[@class='remove-underline']//ion-img)[" + i + "]"));
            moveToEle(element);
            element.click();
        } catch (Exception ee) {
            element = driver.findElement(By.xpath("(//a[@class='remove-underline']//ion-img)[" + i + "]"));
            moveToEle(element);
            element.click();
            try {
                element = driver.findElement(By.xpath("(//a[@class='remove-underline']//ion-img)[" + i + "]"));
                moveToEle(element);
                element.click();
            } catch (Exception e) {
                try {
                    moveToEle(NEXT_BTN_UnderPage);
                    clickProperty(NEXT_BTN_UnderPage);
                    checkImages();
                } catch (Exception ex) {
                    throw new Exception("Next button is not displayed at bottom of page----->>>>");
                }
            }
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
         String i = split[0];
         startingRecordCount = Integer.parseInt(i);
        totalRecords = split[1];
        System.out.println("Total Records :-" + totalRecords + " & record list :- " + text);
    }

    private void getRecordCount_End(WebElement element) {
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
        String i = split[0];
       Integer startingRecordCount = Integer.parseInt(i);
        String totalRecords = split[1];
        System.out.println("Total Records :-" + totalRecords + ", startingRecordCount:-"+startingRecordCount+" & record list :- " + text);
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

    private void fixedLoopToClickNextBtn() throws Exception {
        final int allRec = Integer.parseInt(totalRecords);
        try {
            do {
                waitProperty(nextButton2);
                waitProperty(nextButton2);
                readDataToExcel();  //excel reader
                pageLoad();
                clickProperty(nextButton2);
                Thread.sleep(2000);
                pageLoad();
                pageLoad();
                pageLoad();
                System.out.println("clicked account " +startingRecordCount);
                startingRecordCount++;
                if(!(allRec >startingRecordCount)){
                    getRecordCount_End(recordCount);
                }
            } while (allRec>startingRecordCount);
        }
        catch (WebDriverException e) {
            try {
                refreshProperty();
            } catch (Exception ex) {
                pageLoad();
                pageLoad();
            }
            try {
                while (primeMatches_btn.isDisplayed()) {
                    pageLoad();
                    closeWindow(2);
                    selectWindow(1);
                    pageLoad();
                    pageLoad();

                    try {
                        if (bottomNext_btn.isEnabled()) {
                            moveToEle(bottomNext_btn);
                            clickProperty(bottomNext_btn);
                            System.out.println("clicked bottom next button");
                        }
                    } catch (Exception ee) {
                        try {
                            if (bottomNext_btn.isEnabled()) {
                                moveToEle(bottomNext_btn);
                                clickProperty(bottomNext_btn);
                                System.out.println("clicked bottom next button");
                            }
                        } catch (Exception e1) {
                            throw new Exception("---->>>>> next button is not displayed");
                        }
                    }

                    checkImages();
                }
            } catch (NoSuchElementException ex) {
                fixedLoopToClickNextBtn();
            }
        }

        ExcelToHtml html = new ExcelToHtml();
        html.htmlReport();      //generate html report
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
        Bharath_DataProvider dp = new Bharath_DataProvider();
        dp.readBharathData(list, destinationOfImg_png);

    }

    private String destinationOfImg_png;
   private String name_date_ofImg;
    private void screenShots(String nameTxt) {
        String dtTim;
        try {
             dtTim = getCurrentTimeDate();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotFolder = FolderPaths.SCREENSHOTS;
            File destination = new File(screenshotFolder+ nameTxt +dtTim+".png");
             destinationOfImg_png = String.valueOf(destination);
             name_date_ofImg = nameTxt.replace(" ","")+dtTim.replace("-","");
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

        String dtTim = s1 + s2;
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

    public void mixedProfiles() {
        try {
            moveToEle(Expolre_matches_yadav_Profile);
            moveToEle(Expolre_matches_yadav_Profile);
            moveToEle(Expolre_matches_yadav_Profile);
            moveToEle(Expolre_matches_yadav_Profile);
            clickProperty(Expolre_matches_yadav_Profile);
            try {
                pageLoad();
                pageLoad();
                closeWindow(1);
                selectWindow(1);
                refreshProperty();
                pageLoad();
                pageLoad();
            } catch (Exception e) {
                pageLoad();
                pageLoad();
            }
        } catch (Exception e) {
            mixedProfiles();
        }
    }


    public void mavenParams() throws Exception {
        Parameters p = new Parameters();
        p.getParameters();
        if (p.getJenkins().equalsIgnoreCase("true")) {
            primeSelected(Boolean.valueOf(p.getPrime()));
            selectTab(p.getTab());
        }else {
            primeSelected(true);
            selectTab("3");
        }
    }
}
