package io.github.elli.models;

import java.io.Serializable;
import java.util.List;

public class Websites implements Serializable {
    private List<String> Websites;

    public List<String> getWebsites() {
        return Websites;
    }

    public void setWebsites(List<String> websites) {
        Websites = websites;
    }

    public int size() {
        return Websites.size();
    }

    public String get(int i) {
        return Websites.get(i);
    }
}
