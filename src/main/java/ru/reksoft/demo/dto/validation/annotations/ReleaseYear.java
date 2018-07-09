package ru.reksoft.demo.dto.validation.annotations;

import ru.reksoft.demo.dto.validation.validators.ReleaseYearValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ReleaseYearValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReleaseYear {

    String message() default "{reksoft.demo.validation.constraints.ReleaseYear.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
