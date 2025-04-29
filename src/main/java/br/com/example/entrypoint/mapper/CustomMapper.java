package br.com.example.entrypoint.mapper;

public interface CustomMapper {
  <D> D map(Object source, Class<D> destinationType);
}