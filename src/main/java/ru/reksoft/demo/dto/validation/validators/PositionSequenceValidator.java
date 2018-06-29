package ru.reksoft.demo.dto.validation.validators;

import ru.reksoft.demo.dto.CompositionDTO;
import ru.reksoft.demo.dto.validation.annotations.PositionSequence;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PositionSequenceValidator implements ConstraintValidator<PositionSequence, List<CompositionDTO>> {

    @Override
    public boolean isValid(List<CompositionDTO> compositions, ConstraintValidatorContext constraintValidatorContext) {
        if ((compositions == null) || (compositions.size() == 0)) {
            return true;
        }

        try {
            int[] seqCheck = new int[compositions.size()];
            for (CompositionDTO composition : compositions) if ((++seqCheck[composition.getPosition() - 1]) > 1) return false;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }

        return true;
    }
}