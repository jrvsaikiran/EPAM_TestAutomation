package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingletonPattern {

    WebDriver driver;
    private static volatile SingletonPattern patern;

    private SingletonPattern() {
    }
    public static SingletonPattern getInstance() {
      if (patern == null) {
          synchronized (SingletonPattern.class) {
              if (patern == null) {
                  patern = new SingletonPattern();
              }
          }
      }
        return patern;
    }

    public WebDriver method(){
        return driver;
    }
}
