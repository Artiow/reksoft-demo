package ru.reksoft.demo.mapper;

import java.util.Collection;
import java.util.PriorityQueue;

public interface AbstractEntitySortedMapper<E, D> {

    PriorityQueue<D> toSortedDTO(Collection<E> entityCollection);

    Collection<E> toEntity(PriorityQueue<D> dtoPriorityQueue);
}
