package org.qateam.capital.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.qateam.capital.config.configurations.RetryConfig;
import org.qateam.capital.config.configurations.TimeoutConfig;
import org.qateam.capital.config.configurations.WebConfig;
import org.qateam.capital.config.configurations.BrowserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class AppConfig {

    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

    @JsonProperty("base")
    private WebConfig webConfig;

    @JsonProperty("browser")
    private BrowserConfig browserConfig;

    @JsonProperty("timeout")
    private TimeoutConfig timeoutConfig;

    @JsonProperty("retry")
    private RetryConfig retryConfig;

    private static volatile AppConfig instance;
    private static final Object lock = new Object();
    private static final String DEFAULT_CONFIG_FILE = "application.yml";

    private AppConfig() {}

    public static AppConfig getInstance() {
        if (instance == null){
            synchronized (lock) {
                if (instance == null){
                    instance = initConfig();
                }
            }
        }
        return instance;
    }

    private static AppConfig initConfig() {
        String profile = System.getProperty("profile", "default");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            AppConfig config = loadYmlConfig(mapper, DEFAULT_CONFIG_FILE);
            if (config == null) throw new RuntimeException("Failed to load application.yml");

            if (!"default".equals(profile)) {
                String profileConfigFile = "application-" + profile + ".yml";
                AppConfig profileConfig = loadYmlConfig(mapper, profileConfigFile);
                if (profileConfig != null) config.merge(profileConfig);
            }
            return config;
        } catch (Exception e){
            throw new RuntimeException("Failed to load configuration", e);
        }

    }

    private void merge(AppConfig other) {
        if (other == null) return;

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields){
            if (Modifier.isStatic(field.getModifiers())) continue;

            field.setAccessible(true);
            try{
                Object otherValue = field.get(other);
                if (otherValue != null){
                    field.set(this, otherValue);
                }
            } catch (IllegalAccessException e) {
                log.warn("Failed to merge field {} : {}", field, e.getMessage());
            }
        }
    }

    private static AppConfig loadYmlConfig(ObjectMapper mapper, String fileName) {
        try (InputStream is = AppConfig.class.getClassLoader().getResourceAsStream(fileName)){
            if (is == null) return null;
            return mapper.readValue(is, AppConfig.class);
        } catch (IOException e) {
            log.warn("Failed to load {}: {}", fileName, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public WebConfig getWebConfig() {return webConfig;}

    public BrowserConfig getBrowserConfig() {
        return browserConfig;
    }

    public RetryConfig getRetryConfig() {
        return retryConfig;
    }
}
