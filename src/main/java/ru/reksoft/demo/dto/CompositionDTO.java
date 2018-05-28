package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.CompositionEntity;

import java.time.LocalTime;
import java.util.Comparator;

public class CompositionDTO implements Comparable<CompositionDTO> {

    private Integer id;
    private String name;
    private LocalTime duration;
    private Integer position;


    public CompositionDTO(CompositionEntity entity) {
        id = entity.getId();
        name = entity.getName();
        duration = entity.getDuration().toLocalTime();
        position = entity.getPosition();
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public Integer getPosition() {
        return position;
    }


    @Override
    public int compareTo(CompositionDTO o) {
        return (position - o.position);
    }

    public static class ComparatorByPosition implements Comparator<CompositionDTO> {
        @Override
        public int compare(CompositionDTO o1, CompositionDTO o2) { return o1.compareTo(o2); }
    }
}
