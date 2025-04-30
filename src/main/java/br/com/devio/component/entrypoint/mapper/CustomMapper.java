package br.com.devio.component.entrypoint.mapper;

public interface CustomMapper {
  <D> D map(Object source, Class<D> destinationType);
}