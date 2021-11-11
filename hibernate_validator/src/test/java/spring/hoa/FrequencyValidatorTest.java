package spring.hoa;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

class FrequencyValidatorTest {

    private static Validator validator;

    @BeforeAll
    static void beforeAll() {
        try (final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    @Test
    void shouldReturnViolation_whenCollectionHasDuplicatedElements() {
        final List<Integer> integers = Arrays.asList(1, 2, 1, 3, 4, 1, 3, 3, 3);
        final Set<ConstraintViolation<Element>> validate = validator.validate(new Element(integers));

        Assertions.assertThat(validate).hasSize(1);

        final ConstraintViolation<Element> violation = validate.iterator().next();

        Assertions.assertThat(violation.getMessage()).isEqualTo("List of '%s' contained element with frequency greater than the '%s'.",
                integers,
                1);
    }

    @Test
    void shouldReturnViolation_whenCollectionHasNoDuplicatedElements() {
        final List<Integer> integers = Arrays.asList(1, 2, 5, 3, 4);
        final Set<ConstraintViolation<Element>> validate = validator.validate(new Element(integers));
        Assertions.assertThat(validate).isEmpty();
    }

    @RequiredArgsConstructor
    public static final class Element {
        @Frequency
        private final List<Integer> collection;
    }
}