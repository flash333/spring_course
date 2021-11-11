package spring.hoa;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FrequencyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Frequency {

    int maximum() default 1;
    String message() default "List of '${validatedValue}' contained element with frequency greater than the '{maximum}'.";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
