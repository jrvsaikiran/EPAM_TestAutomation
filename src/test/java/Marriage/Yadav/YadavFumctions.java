package Marriage.Yadav;

import Marriage.Bharath.FolderPaths;
import Marriage.pdfExcelImg.Convert_Image_To_PDF;
import Marriage.pdfExcelImg.Parameters;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.JsonException;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class YadavFumctions {
    private WebDriver driver;
    public static Parameters par;

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

    @FindBy(xpath = "(//div[@id='topnav-login-menu']/div/div)[1]/a")
    WebElement homeTab_mixed;

    @FindBy(xpath = "(//div[@id='topnav-login-menu']/div/div)[2]/a")
    WebElement matchesTab_mixed;

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

    @FindBy(xpath = "//div[@id='SurveyPopupClose']")
    WebElement cancleSurvey;

    public void loginFunction() throws Exception {
        pageLoad();
        send(username, "YDV972623");
        send(password, "9440741024");
        clickProperty(login_btn);
        pageLoad();
//        clickProperty(account_btn);
        pageLoad();
        try {
            clickProperty(skip_btn);
        } catch (TimeoutException e) {
            switchWindow(1);
        }
    }

    public void selectPrime(boolean prime) {
        Actions action = new Actions(driver);
        refreshProperty();
        try {
            if (prime) {
                pageLoad();
                action.doubleClick(prime_btn).build().perform();
                clickProperty(prime_btn);
                pageLoad();
                System.out.println("Switched to PRIME mode");
            }
        } catch (Exception e) {

        }
    }



    public void switchToSpecificTab(String str) throws Exception {
        pageLoad();
        if(System.getProperty("testng.xml").equalsIgnoreCase("yadav.xml")){
            if (Integer.parseInt(str) <= 2) {
                pageLoad();
                moveToEle(home_tab);
                moveToEle(home_tab);
                moveToEle(home_tab);
                pageLoad();
            } else {
                pageLoad();
                moveToEle(matches_tab);
                moveToEle(matches_tab);
                moveToEle(matches_tab);
                pageLoad();
            }
        } else if (System.getProperty("testng.xml").equalsIgnoreCase("mixed.xml")) {

        }
        {
            if (Integer.parseInt(str) <= 2) {
                pageLoad();
                moveToEle(homeTab_mixed);
                moveToEle(homeTab_mixed);
                moveToEle(homeTab_mixed);
                pageLoad();
            } else {
                pageLoad();
                moveToEle(matchesTab_mixed);
                moveToEle(matchesTab_mixed);
                moveToEle(matchesTab_mixed);
                pageLoad();
            }
        }

        switch (str) {
            case "1":
                clickProperty(viewedMyProfile);
                pageLoad();
                break;
            case "2":
                clickProperty(profileViewed_NotContacted);
                pageLoad();
                break;
            case "3":
                clickProperty(membersWhoMightLikeYou);
                break;
            case "4":
                clickProperty(latestMatches);
                break;
            case "5":
                clickProperty(yetToBeViewed);
                break;
            case "6":
                clickProperty(mutualMatches);
                break;
            default:
                throw new Exception("mention proper tab number---->>>");

        }

    }

    public void firstPicClick() throws Exception {
        pageLoad();
        try {
            if(firstPhoto.isDisplayed()){
                clickProperty(firstPhoto);
                switchWindow(2);
            }
        } catch (Exception e) {
            try {
                pageLoad();
                pageLoad();
                if(firstPhoto.isDisplayed()){
                    clickProperty(firstPhoto);
                    switchWindow(2);
                }
            }catch (Exception e2){
                try {
                    pageLoad();
                    if(firstPhoto.isDisplayed()){
                        clickProperty(firstPhoto);
                        switchWindow(2);
                    }
                }catch (Exception e3){
                    throw new RuntimeException("No phots ara available for view------>>>>>>>");
                }
            }

        }

    }

    private static int iterations = 1;

    public void pageIterations() throws Exception {
        try {
            if(cancleSurvey.isDisplayed()){
                clickProperty(cancleSurvey);
            }
        }catch (Exception ee){
            pageLoad();
            pageLoad();
        }
        pageLoad();
        pageLoad();
        nextIteration(1);
        bottomNextButton();
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

    private void refreshProperty() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            refreshProperty();
        }
    }
    private void bottomNextButton() {
        try {
            if (isBottomPageNextBtnDisplayed()) {
                clickProperty(bottomPageNext_btn);
                pageLoad();
                System.out.println(" clicked BOTTOM NEXT button:- " + iterations);
                iterations++;
            }
        } catch (Exception e) {
            bottomNextButton();
        }
    }

    private static int j = 1;

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
        WebElement nextBtn = null;
        pageLoad();
        pageLoad();
        pageLoad();
        try {
            nextBtn = driver.findElement(By.xpath("(//button[@id='nxtproflink'])[" + i + "]"));
            if (nextBtn.isDisplayed()) {
                wait(nextBtn);
                moveToEle(nextBtn);
                moveToEle(nextBtn);
                pageLoad();
                pageLoad();
                moveToEle(nextBtn);
                moveToEle(nextBtn);
                getDataToExcel(i);
                clickProperty(nextBtn);
                Thread.sleep(2000);
                pageLoad();
                pageLoad();
            }

        } catch (WebDriverException e) {
            try {
                if (!nextBtn.isDisplayed()) {
                    throw new Exception("Next button not displayed " + nextBtn);
                }
            } catch (NullPointerException | WebDriverException n) {
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

    private void getDataToExcel(int i) {

        try {
            WebElement name = driver.findElement(By.xpath("(//span[starts-with(@id,'dispname')])["+i+"]"));
            String nameTxt = getEleTest(name);

            WebElement age = driver.findElement(By.xpath("(//span[starts-with(@id,'dispname')])["+i+"]/..//following-sibling::div[starts-with(@class,'pro-detail')]"));
            String ageTxt = getEleTest(age);

            WebElement education = driver.findElement(By.xpath("(//span[starts-with(@id,'dispname')])["+i+"]/..//following-sibling::div[starts-with(@class,'edu')]"));
            String educationTxt = getEleTest(education);

            WebElement loaction = driver.findElement(By.xpath("(//span[starts-with(@id,'dispname')])["+i+"]/..//following-sibling::div[starts-with(@class,'location')]"));
            String locationTxt = getEleTest(loaction);

            WebElement activity = driver.findElement(By.xpath("(//span[starts-with(@id,'dispname')])["+i+"]/..//following-sibling::div[contains(@ng-if,'Online now')]"));
            String activityTxt = getEleTest(activity);

            String profileNumber = profileId(i);

            String finalCast ;

            if(par.getTestngXml().equalsIgnoreCase("mixed.xml")){
                WebElement cast = driver.findElement(By.xpath("((//div[normalize-space()='Religious Information'])["+i+"]/..//ul/li)[3]/div/span[starts-with(@class,'input-data')]"));
                String castTxt = getEleTest(cast);

                WebElement subCast = driver.findElement(By.xpath("((//div[normalize-space()='Religious Information'])["+i+"]/..//ul/li)[2]/div/span[starts-with(@class,'input-data')]"));
                String subCastTxt = getEleTest(subCast);

                 finalCast = castTxt +"<<>>"+ subCastTxt;
            }else {
                WebElement subCast = driver.findElement(By.xpath("((//div[normalize-space()='Religious Information'])["+i+"]/..//ul/li)[1]/div/span[starts-with(@class,'input-data')]"));
                finalCast = getEleTest(subCast);
            }


            List<CustomerData> list = new ArrayList<>();
            list.add(new CustomerData(nameTxt,ageTxt,educationTxt,locationTxt,activityTxt,profileNumber,finalCast));

            LinkedHashMap<Integer,List<CustomerData>> map = new LinkedHashMap<>();
            map.put(i,list);
//            System.out.println(map);
//            List<CustomerData> customerData = map.get(i);
//            CustomerData customerData1 = customerData.get(0);
//            System.out.println(customerData1);
//            System.out.println(customerData1.getAge());
            screenShots(nameTxt); //take screenshot
            Yadav_DataProvider dp = new Yadav_DataProvider();
            dp.readData(map,i,par,destinationOfImg_png);

        } catch (Exception e) {
            getDataToExcel(i);
        }
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

    private String profileId(int i) {
        String eleTest;
        if(i==1){
            WebElement element = driver.findElement(By.xpath("//span[@id='oppositeMemberId']/.."));
             eleTest = getEleTest(element);
        }else {
            WebElement element = driver.findElement(By.xpath("((//scroll-details[@ng-show='infMemberInfo'])["+(i-1)+"]//div[@class='fleft']//div)[1]"));
             eleTest = getEleTest(element);
        }
        return eleTest;
    }

    private String text;
    private String getEleTest(WebElement ele){

        try {
             text = ele.getText();
        } catch (Exception e) {
           try {
               text = ele.getText();
           }catch (Exception e1) {
               try {
                   text = ele.getText();
               } catch (Exception ex) {
                   text = ele.getText();
               }
           }
        }
        return text;
    }


    private static int nextBtn_inPages = 1;

    private void handleBottomNextBtn() throws Exception {
        closeWindow(2);
        switchWindow(1);
//        moveToEle(bottomNext_btn);
        try {
            if (bottomNext_btn.isDisplayed()) {
                moveToEle(bottomNext_btn);
                clickProperty(bottomNext_btn);
                System.out.println("Clicked Bottom NEXT button:- " + nextBtn_inPages);
            }
        } catch (NoSuchElementException e) {
            try {
                if (bottomNext_btn.isDisplayed()) {
                    moveToEle(bottomNext_btn);
                    clickProperty(bottomNext_btn);
                    System.out.println("Clicked Bottom NEXT button:- " + nextBtn_inPages);
                }
            } catch (Exception ex) {
                throw new Exception("Next button is not displayed");
            }
        } catch (Exception e) {
            handleBottomNextBtn();
        }
        nextBtn_inPages++;
    }


    private void clickProperty(WebElement element) {
        try {
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
            fluentWait.ignoring(WebDriverException.class)
                    .pollingEvery(Duration.ofSeconds(1))
                    .withTimeout(Duration.ofSeconds(9))
                    .until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (WebDriverException e) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            } catch (Exception ex) {
                FluentWait<WebDriver> fluentWait = new FluentWait<>(driver);
                fluentWait.ignoring(WebDriverException.class)
                        .pollingEvery(Duration.ofSeconds(2))
                        .withTimeout(Duration.ofSeconds(9))
                        .until(ExpectedConditions.elementToBeClickable(element)).click();
            }
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
            refreshProperty();
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
            refreshProperty();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(str);
        }
    }

    private void switchWindow(int stop) throws Exception {
        try {
            int i = 1;
            Set<String> win = driver.getWindowHandles();
            for (String windowHandle : win) {
                if (stop == i) {
                    driver.switchTo().window(windowHandle);
                    System.out.println("driver switched to >>" + stop + "<< window of title " + driver.getTitle());
                }
                i++;
            }
        } catch (Exception e) {
            throw new Exception("unable to handle window");
        }
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

    private void moveToEle(WebElement ele) {
        Actions action = new Actions(driver);
        try {
            action.moveToElement(ele).build().perform();
        } catch (JsonException e2){

            pageLoad();
            action.moveToElement(ele).build().perform();
            moveToEle(ele);
        }
        catch (WebDriverException e) {
            action.moveToElement(ele).build().perform();
        }
        catch (Exception e) {
            action.moveToElement(ele).build().perform();
        }
    }


    public void mavenParameters(YadavFumctions test) throws Exception {

         par = new Parameters();
        par.getParameters();

        if (par.getJenkins().equalsIgnoreCase("true")) {
            test.selectPrime(Boolean.valueOf(par.getPrime()));
            test.switchToSpecificTab(par.getTab());
        } else {
            test.selectPrime(false);
            test.switchToSpecificTab("6");
        }
    }
}
