package org.qateam.capital.config.configurations;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WebConfig(

        @JsonProperty("url")
        String url
) {

}
