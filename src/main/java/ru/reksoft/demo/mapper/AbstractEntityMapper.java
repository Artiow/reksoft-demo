package ru.reksoft.demo.mapper;

public interface AbstractEntityMapper<E, D> {

    D toDTO(E entity);

    E toEntity(D dto);
}
