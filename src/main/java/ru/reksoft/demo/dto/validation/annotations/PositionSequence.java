package ru.reksoft.demo.dto.validation.annotations;

import ru.reksoft.demo.dto.validation.validators.PositionSequenceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PositionSequenceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PositionSequence {

    String message() default "{reksoft.demo.validation.constraints.PositionSequence.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
