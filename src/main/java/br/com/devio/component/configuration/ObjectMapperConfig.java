package br.com.devio.component.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ObjectMapperConfig {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        configureObjectMapper();
    }

    private static void configureObjectMapper() {
        try (InputStream input = ObjectMapperConfig.class.getClassLoader().getResourceAsStream("application.yaml")) {
            if (input != null) {
                Yaml yaml = new Yaml();
                Map<String, Object> config = yaml.load(input);

                // Example: Apply configurations from YAML
                Map<String, Object> jacksonConfig = (Map<String, Object>) config.get("jackson");
                if (jacksonConfig != null) {
                    if (Boolean.FALSE.equals(jacksonConfig.get("write-dates-as-timestamps"))) {
                        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load ObjectMapper configuration from application.yaml", e);
        }

        objectMapper.registerModule(new JavaTimeModule());
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}