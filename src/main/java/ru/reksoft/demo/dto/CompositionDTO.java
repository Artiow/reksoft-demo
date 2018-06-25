package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import javax.validation.constraints.*;
import java.time.LocalTime;

public class CompositionDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdCheck.class)
    @Min(value = 1, groups = IdCheck.class)
    private Integer id;

    @NotNull(groups = FieldCheck.class)
    @Size(min = 1, max = 45, groups = FieldCheck.class)
    private String name;

    @NotNull(groups = FieldCheck.class)
    @Min(value = 1, groups = FieldCheck.class)
    private Integer position;

    @NotNull(groups = FieldCheck.class)
    private LocalTime duration;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public CompositionDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompositionDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPosition() {
        return position;
    }

    public CompositionDTO setPosition(Integer position) {
        this.position = position;
        return this;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public CompositionDTO setDuration(LocalTime duration) {
        this.duration = duration;
        return this;
    }

    public interface IdCheck {

    }

    public interface FieldCheck {

    }
}
