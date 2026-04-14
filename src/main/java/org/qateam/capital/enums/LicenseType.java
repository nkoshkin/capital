package org.qateam.capital.enums;

public enum LicenseType {
    ASIC("/en-au"),
    FCA("/en-gb"),
    SCA("/en-ae"),
    SCB(""),
    CYSEC("");

    private final String path;

    LicenseType(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
