package io.github.elli.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverManager {

    WebDriver driver;

        static void setupClass() {
            WebDriverManager.chromedriver().setup();
        }

        void setupScrape() {
            driver = new ChromeDriver();
        }

        public void get(String website) {
            driver.get(website);
        }

        void quitScraping() {
            driver.quit();
        }




    public WebElement findElement(By details_zu) {
        WebElement element = driver.findElement(details_zu);
        return element;
    }
}
