package ru.reksoft.demo.mapper;

import java.util.Collection;

public interface AbstractEntityMapper<E, D> {

    D toDTO(E entity);

    Collection<D> toDTO(Collection<E> entityCollection);

    E toEntity(D dto);

    Collection<E> toEntity(Collection<D> dtoCollection);
}
