package io.github.elli;

import io.github.elli.config.JsonParser;
import io.github.elli.config.WebdriverManager;
import io.github.elli.scraper.Scraper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {

        Scraper scraper = new Scraper();
        try {
            scraper.run(args);
        } catch (Exception e) {
            LOGGER.error("Something bad happened :)", e);
        }
    }
}
