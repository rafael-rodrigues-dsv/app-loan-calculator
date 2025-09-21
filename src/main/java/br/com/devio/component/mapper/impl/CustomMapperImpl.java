package br.com.devio.component.mapper.impl;

import br.com.devio.component.mapper.CustomMapper;
import br.com.devio.component.utils.ObjectMapperUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomMapperImpl implements CustomMapper {

    private final ObjectMapper objectMapper;

    public CustomMapperImpl() {
        this.objectMapper = ObjectMapperUtils.getObjectMapper();
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return objectMapper.convertValue(source, destinationType);
    }
}
