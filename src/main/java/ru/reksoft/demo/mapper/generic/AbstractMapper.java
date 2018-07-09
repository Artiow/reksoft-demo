package ru.reksoft.demo.mapper.generic;

import ru.reksoft.demo.domain.generic.DomainObject;
import ru.reksoft.demo.dto.generic.DataTransferObject;

import java.util.Collection;
import java.util.List;

public interface AbstractMapper<E extends DomainObject, D extends DataTransferObject> {

    D toDTO(E entity);

    List<D> toDTO(Collection<E> entityCollection);

    E toEntity(D dto);

    Collection<E> toEntity(List<D> dtoCollection);
}
