package org.qateam.capital.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class URLBuilder {
    public static String build(String url, String path) throws URISyntaxException {
        URI baseUri = new URI(url).normalize();
        URI fullUri = baseUri.resolve(path);
        return fullUri.toString();
    }
}
