package ru.reksoft.demo.dto.validation.validators;

import ru.reksoft.demo.dto.validation.annotations.ReleaseYear;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class ReleaseYearValidator implements ConstraintValidator<ReleaseYear, Integer> {

    @Override
    public boolean isValid(Integer field, ConstraintValidatorContext cxt) {
        return (field != null) && (field > 0) && (field <= LocalDateTime.now().getYear());
    }
}
