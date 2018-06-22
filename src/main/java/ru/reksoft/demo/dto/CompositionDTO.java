package ru.reksoft.demo.dto;

import ru.reksoft.demo.dto.generic.AbstractIdentifiedDTO;

import javax.validation.constraints.*;
import java.time.LocalTime;

public class CompositionDTO extends AbstractIdentifiedDTO {

    @NotNull(groups = IdentifierCheck.class)
    @Min(value = 1, groups = IdentifierCheck.class)
    private Integer id;

    @NotNull(groups = CreateCheck.class)
    @NotEmpty(groups = UpdateCheck.class)
    @Size(min = 1, max = 45, groups = UpdateCheck.class)
    private String name;

    @NotNull(groups = CreateCheck.class)
    @Min(value = 1, groups = UpdateCheck.class)
    private Integer position;

    @NotNull(groups = UpdateCheck.class)
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

    public interface IdentifierCheck extends UpdateCheck {

    }

    public interface CreateCheck extends UpdateCheck {

    }

    public interface UpdateCheck {

    }
}
