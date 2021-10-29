package io.github.elli.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class JsoupValidateContent {
//    private final File file;
    public static final Logger LOGGER = LoggerFactory.getLogger(JsoupValidateContent.class);

    public JsoupValidateContent(String newLinks) throws IOException {
//        file = new File("input.html");
//        BufferedWriter htmlWriterTEST = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        Document doc = Jsoup.connect(newLinks).get();
//        doc.select(("div[class=\"bg-white\"]").outerHtml());
//        if(getContent(doc)) {
//            try {
//                htmlWriterTEST.write(doc.select("div[class=\"bg-white\"]").outerHtml());
//                htmlWriterTEST.flush();
//                htmlWriterTEST.close();
//            } catch (IOException e) {
//                LOGGER.error("Writer got interrupted", e);
//            }
//        }
    }

    private boolean getContent(Document doc) {
        try {
            doc.select("a[href=\"https://maps.google.com/maps\"]");
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
