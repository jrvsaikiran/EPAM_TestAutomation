package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class SingletonPattern {

    private static  ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private static WebDriver driver;

    public static WebDriver getInstance(String browser) {
        if (tlDriver.get() == null) {
            synchronized (SingletonPattern.class) {
                if (tlDriver.get() == null) {
                     driver = getDriver(browser);
                    BaseTest.extentLogs.info("Driver created");
                }
            }
        }
        return driver;
    }

    private static WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver());
                BaseTest.extentLogs.info("Chrome driver set");
                return tlDriver.get();
            case "firefox":
                FirefoxOptions options = new FirefoxOptions();
                options.setBrowserVersion("128");
                tlDriver.set(new FirefoxDriver(options));
                BaseTest.extentLogs.info("Firefox driver set");
                return tlDriver.get();
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setBrowserVersion("127");
                tlDriver.set(new EdgeDriver(edgeOptions));
                BaseTest.extentLogs.info("Edge driver set");
                return tlDriver.get();
            case "safari":
                WebDriverManager.safaridriver().setup();
                tlDriver.set(new SafariDriver());
                BaseTest.extentLogs.info("Safari driver set");
                return tlDriver.get();
            default:
                throw new RuntimeException("Unsupported browser---> " + browser);
        }
    }

    public static void quit() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
            BaseTest.extentLogs.info("driver quit");

        }
    }
}
