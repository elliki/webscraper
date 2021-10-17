package io.github.elli.scraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.elli.config.JsonParser;
import io.github.elli.models.Websites;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;


public class Scraper {
    public static final Logger LOGGER = LoggerFactory.getLogger(Scraper.class);

    private Set<String> linkVisited = new HashSet<>();
    private WebDriver driver;

    public Scraper() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void run(String[] args) throws IOException, URISyntaxException {
        JsonParser parser = new JsonParser();
        Websites websites = parser.parse();
        try {
            scrapeLinksRecursively(websites.getWebsites());
        } catch (InterruptedException e) {
            LOGGER.error("Thread got interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    public void scrapeLinksRecursively(List<String> websiteLinks) throws InterruptedException {
        if(websiteLinks == null || websiteLinks.isEmpty()) {
            return;
        }

        List<String> zwischenListe = new ArrayList<>();
        for (String link: websiteLinks) {
            driver.get(link);
            Thread.sleep(50);
            zwischenListe.addAll(driver.findElements(By.tagName("a")).stream()
                    .map(this::extractHref)
                    .collect(Collectors.toList()));
            linkVisited.add(link);
        }

        List<String> newLinks = zwischenListe.stream()
                .filter(this::hasLinkNotBeenVisited)
                .filter(Objects::nonNull)
                .filter(this::isNotExternalLink)
                .collect(Collectors.toList());

        scrapeLinksRecursively(newLinks);
    }

    private boolean isNotExternalLink(String link) {
        return link.contains("https://www.mitkids-unterwegs.de/") ||  link.contains("https://www.karlsruhe.de");
    }

    private String extractHref(WebElement webElement) {
        try {
            System.out.println(webElement.getAttribute("href"));
            return webElement.getAttribute("href");
        }
        catch (Exception e) {
            LOGGER.warn("could not extract link from element. Error was:");
            return null;
        }

    }

    private boolean hasLinkNotBeenVisited(String link) {
        return !linkVisited.contains(link);
    }
}



