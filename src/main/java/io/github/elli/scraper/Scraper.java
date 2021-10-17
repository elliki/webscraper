package io.github.elli.scraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.elli.config.JsonParser;
import io.github.elli.models.Websites;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class Scraper {

    static List<String> linkVisited = new ArrayList<String>();
    static List<String> linkNew = new ArrayList<String>();
    static List<WebElement> zwischenListe =new ArrayList<WebElement>();
    public static final Logger LOGGER = LoggerFactory.getLogger(Scraper.class);

    public void run(String[] args) throws IOException, URISyntaxException {
        JsonParser parser = new JsonParser();
        Websites websites = parser.parse();
        linkNew.addAll(websites.getWebsites());
        firstScrape(linkNew);
    }

    public void firstScrape(List<String> website) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        for (String test: website) {
            zwischenListe.addAll(driver.findElements(By.tagName("a")));
            driver.get(test);
            zwischenListe.addAll(driver.findElements(By.tagName("a")));
            linkVisited.add(test);
        }
        linkNew.clear();
        iterator(zwischenListe);
        zwischenListe.clear();
    }

    private void iterator(List<WebElement> zwischenListe) {
        for (WebElement test : zwischenListe) {
            if (!linkVisited.contains(test.getAttribute("href")) && test.getAttribute("href").contains("https://www.mitkids-unterwegs.de/")) {
                linkNew.add(test.getAttribute("href"));
                break;
            }
        }
        firstScrape(linkNew);
    }
}



