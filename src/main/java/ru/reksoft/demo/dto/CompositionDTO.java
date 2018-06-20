package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;
import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class CompositionDTO extends AbstractIdentifiedDTO {

    @NotNull(message = "name must not be null!", groups = CreateCheck.class)
    @NotEmpty(message = "name must not be empty!", groups = CreateCheck.class)
    @Max(value = 45, message = "name must not be longer than 45 characters!", groups = CreateCheck.class)
    private String name;

    @NotNull(message = "position must not be null!", groups = CreateCheck.class)
    @Min(value = 1, message = "position must not be less than one!", groups = CreateCheck.class)
    private Integer position;

    @NotNull(message = "name must not be null!", groups = CreateCheck.class)
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
}
