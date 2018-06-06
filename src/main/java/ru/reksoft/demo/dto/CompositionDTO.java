package ru.reksoft.demo.dto;

import java.time.LocalTime;

public class CompositionDTO implements Comparable<CompositionDTO> {

    private Integer id;
    private Integer position;
    private String name;
    private LocalTime duration;


    public Integer getId() {
        return id;
    }

    public CompositionDTO setId(Integer id) {
        this.id = id;
        return this;
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
