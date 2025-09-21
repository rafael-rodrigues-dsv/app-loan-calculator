package br.com.devio.component.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class ObjectMapperUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules()
            .configure(WRITE_DATES_AS_TIMESTAMPS, true)
            .registerModule(new JavaTimeModule())
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(WRITE_DATES_AS_TIMESTAMPS, false);

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
