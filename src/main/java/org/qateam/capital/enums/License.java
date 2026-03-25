package org.qateam.capital.enums;

public enum License {
    ASIC("ASIC", "/en-au"),
    FCA("FCA", "/en-gb"),
    SCA("SCA", "/en-ae"),
    SCB("SCB", ""),
    CYSEC("CYSEC", "");

    public final String name;
    public final String path;

    License(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
