package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SingletonPattern {


    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private static volatile SingletonPattern patern;

    private SingletonPattern() {
    }

    public static SingletonPattern getInstance(String browser) {
        if (patern == null) {
            synchronized (SingletonPattern.class) {
                if (patern == null) {
                    patern = new SingletonPattern();
                }
            }
        }

        if (tlDriver.get() == null) {
            patern.getDriver(browser);
        }
        return patern;
    }

    private void getDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver());
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                tlDriver.set(new InternetExplorerDriver());
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                tlDriver.set(new SafariDriver());
                break;
        }

    }

    public WebDriver driver() {
        WebDriver driver = tlDriver.get();
        return driver;
    }

    public static void quit() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();

        }
    }
}
