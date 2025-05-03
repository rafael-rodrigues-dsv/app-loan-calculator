package br.com.devio.component.entrypoint.mapper.impl;

import br.com.devio.component.entrypoint.mapper.CustomMapper;
import br.com.devio.component.domain.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomMapperImpl implements CustomMapper {

    private final ObjectMapper objectMapper;

    public CustomMapperImpl() {
        this.objectMapper = ObjectMapperUtil.getObjectMapper();
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return objectMapper.convertValue(source, destinationType);
    }
}
