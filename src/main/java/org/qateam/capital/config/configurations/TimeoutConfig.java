package org.qateam.capital.config.configurations;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TimeoutConfig(

        @JsonProperty("default")
        int defaultTimeout,

        @JsonProperty("page_load")
        int pageLoad,

        @JsonProperty("element")
        int element
) {
}
