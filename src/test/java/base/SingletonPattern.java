package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class SingletonPattern extends BaseTest{


    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    private static volatile SingletonPattern patern;

    private SingletonPattern() {
    }

    public static SingletonPattern getInstance(String browser) {
        if (patern == null) {
            synchronized (SingletonPattern.class) {
                if (patern == null) {
                    patern = new SingletonPattern();
                    logger.info("Initializing Singleton Pattern");
                }
            }
        }

        if (tlDriver.get() == null) {
            patern.getDriver(browser);
            logger.info("Driver created");
        }

        return patern;
    }

    private void getDriver(String browser) {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver());
                logger.info("Chrome driver set");
                break;
            case "firefox":
                FirefoxOptions options = new FirefoxOptions();
                options.setBrowserVersion("128");
                tlDriver.set(new FirefoxDriver(options));
                logger.info("Firefox driver set");
                break;
            case "edge":
                EdgeOptions edgeOptions= new EdgeOptions();
                edgeOptions.setBrowserVersion("126");
                tlDriver.set(new EdgeDriver(edgeOptions));
                logger.info("Edge driver set");
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                tlDriver.set(new SafariDriver());
                logger.info("Safari driver set");
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
            logger.info("driver quit");

        }
    }
}
