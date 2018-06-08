package ru.reksoft.demo.mapper;

import java.util.Collection;
import java.util.List;

public interface AbstractEntityMapper<E, D> {

    D toDTO(E entity);

    List<D> toDTO(Collection<E> entityCollection);

    E toEntity(D dto);

    Collection<E> toEntity(List<D> dtoCollection);
}
