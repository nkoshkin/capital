package org.qateam.capital.licenses;

public enum License {
    ASIC("ASIC", "/en-au");

    public final String name;
    public final String path;

    License(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
