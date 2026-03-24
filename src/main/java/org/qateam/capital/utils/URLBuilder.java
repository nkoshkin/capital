package org.qateam.capital.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class URLBuilder {
    public static String build(String url, String path) {

        URI fullUri = null;
        try {
            URI baseUri = new URI(url).normalize();
            fullUri = baseUri.resolve(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return fullUri.toString();
    }
}
