package spring.hoa;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class FrequencyValidator implements ConstraintValidator<Frequency, Collection<Integer>> {

    private int maximum;

    @Override
    public void initialize(Frequency constraintAnnotation) {
        final int max = constraintAnnotation.maximum();
        if (max < 1) {
            throw new IllegalArgumentException("Maximum must be at least 1");
        }
        maximum = max;
    }

    @Override
    public boolean isValid(Collection<Integer> integers, ConstraintValidatorContext constraintValidatorContext) {
        Set<Integer> values = new HashSet<>(integers);
        return values.stream().allMatch(v -> isMaxEqualsWith(Collections.frequency(integers, v)));
    }

    private boolean isMaxEqualsWith(int number) {
        return number == maximum;
    }
}
