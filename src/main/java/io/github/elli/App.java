package io.github.elli;

import io.github.elli.config.JsonParser;
import io.github.elli.config.WebdriverManager;
import io.github.elli.database.ConfigureMysql;
import io.github.elli.scraper.Scraper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Scraper scraper = new Scraper();
        try (Connection conn = ConfigureMysql.getConnection()) {
            // print out a message
            System.out.printf("Connected to database %s "
                    + "successfully.%n", conn.getCatalog());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            scraper.run(args);
        } catch (Exception e) {
            LOGGER.error("Something bad happened :)", e);
        }
    }
}
