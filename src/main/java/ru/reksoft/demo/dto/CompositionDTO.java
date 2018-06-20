package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import java.time.LocalTime;

public class CompositionDTO extends AbstractIdentifiedDTO implements Comparable<CompositionDTO> {

    private String name;
    private Integer position;
    private LocalTime duration;


    public CompositionDTO setId(Integer id) {
        return (CompositionDTO) super.setId(id);
    }


    public Integer getPosition() {
        return position;
    }

    public CompositionDTO setPosition(Integer position) {
        this.position = position;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompositionDTO setName(String name) {
        this.name = name;
        return this;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public CompositionDTO setDuration(LocalTime duration) {
        this.duration = duration;
        return this;
    }


    @Override
    public int compareTo(CompositionDTO o) {
        return position.compareTo(o.position);
    }
}
