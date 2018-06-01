package ru.reksoft.demo.dto;

import ru.reksoft.demo.domain.CompositionEntity;

import java.time.LocalTime;

public class CompositionDTO implements Comparable<CompositionDTO> {

    private Integer id;
    private String name;
    private LocalTime duration;
    private Integer position;


    public CompositionDTO(CompositionEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.duration = entity.getDuration().toLocalTime();
        this.position = entity.getPosition();
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
}
