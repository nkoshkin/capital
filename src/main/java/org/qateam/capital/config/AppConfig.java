package org.qateam.capital.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.qateam.capital.config.configurations.WebConfig;
import org.qateam.capital.config.configurations.BrowserConfig;

public record AppConfig(
    @JsonProperty("base")
    WebConfig webConfig,

    @JsonProperty("browser")
    BrowserConfig browserConfig
) {
}
