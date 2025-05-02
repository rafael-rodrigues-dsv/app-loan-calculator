package br.com.devio.component.entrypoint.mapper.impl;

import br.com.devio.component.entrypoint.mapper.CustomMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomMapperImpl implements CustomMapper {

    private final ObjectMapper objectMapper;

    public CustomMapperImpl() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        this.objectMapper.registerModule(new com.fasterxml.jackson.module.paramnames.ParameterNamesModule());
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return objectMapper.convertValue(source, destinationType);
    }
}