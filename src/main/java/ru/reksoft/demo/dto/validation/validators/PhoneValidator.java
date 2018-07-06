package ru.reksoft.demo.dto.validation.validators;

import ru.reksoft.demo.dto.validation.annotations.Phone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("^(\\+7-)?[0-9]{3}-?[0-9]{3}-?[0-9]{2}-?[0-9]{2}$");
    }
}
