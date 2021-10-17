package io.github.elli.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.elli.models.Websites;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

public class JsonParser {
    public Websites parse() throws IOException, URISyntaxException {
        // read JSON from a file
        File jsonFile = Path.of(this.getClass().getClassLoader().getResource("json/websites.json").toURI()).toFile();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonFile, Websites.class);
    }

}
