package br.com.devio.component.entrypoint.mapper.impl;

import br.com.devio.component.entrypoint.mapper.CustomMapper;
import org.modelmapper.ModelMapper;

public class CustomMapperImpl implements CustomMapper {

    private final ModelMapper mapper;

    public CustomMapperImpl() {
        this.mapper = new ModelMapper();
        this.mapper.getConfiguration().setAmbiguityIgnored(true);
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        return this.mapper.map(source, destinationType);
    }
}
