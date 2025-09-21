package br.com.devio.component.mapper;

public interface CustomMapper {
  <D> D map(Object source, Class<D> destinationType);
}