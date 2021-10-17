package io.github.elli.scraper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

public class RecursiveLinkTest {
    WebDriver driver;
    static List<String> linkVisited = new ArrayList<String>();

    public RecursiveLinkTest(WebDriver driver) {
        this.driver = driver;
    }
//
    public void linkTest() {

        for (WebElement link : driver.findElements(By.tagName("a"))) {
            if (link.isDisplayed() && !linkVisited.contains(link.getText())) {
                linkVisited.add(link.getText());
                System.out.println(link.getText());
                link.click();
                new RecursiveLinkTest(driver).linkTest();
            }
        }
        driver.navigate().back();
    }
}
