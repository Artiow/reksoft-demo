package ru.reksoft.demo.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.reksoft.demo.dto.AlbumDTO;
import ru.reksoft.demo.dto.generic.checkgroups.CreateCheck;
import ru.reksoft.demo.dto.generic.checkgroups.IdentifierCheck;
import ru.reksoft.demo.dto.generic.checkgroups.UpdateCheck;

import javax.validation.ValidationException;
import javax.validation.Validator;

@Component
public class AlbumValidator {

    private Validator validator;

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    //todo: custom validate!
    public void validate(AlbumDTO albumDTO, Class<?>... classes) throws ValidationException {
        for (Class<?> check: classes) {
            if (check == IdentifierCheck.class) {
                throw new ValidationException();
            } else if (check == CreateCheck.class) {
                throw new ValidationException();
            } else if (check == UpdateCheck.class) {
                throw new ValidationException();
            }
        }
    }
}
