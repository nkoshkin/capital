package org.qateam.capital.config.configurations;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BrowserConfig(

        @JsonProperty("headless")
        boolean headless,

        @JsonProperty("slow_mo")
        int slowMo,

        @JsonProperty("channel")
        String channel,

        @JsonProperty("viewport_width")
        int viewportWidth,

        @JsonProperty("viewport_height")
        int viewportHeight,

        @JsonProperty("locale")
        String locale,

        @JsonProperty("timezone")
        String timezone,

        @JsonProperty("args")
        List<String> args
) {
}
