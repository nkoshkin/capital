package org.qateam.capital.config.configurations;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RetryConfig(
        @JsonProperty("attempts")
        int attempts,

        @JsonProperty("delay")
        int delay
) {
}
