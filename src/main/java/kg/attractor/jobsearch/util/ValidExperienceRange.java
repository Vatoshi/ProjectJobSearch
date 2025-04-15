package kg.attractor.jobsearch.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExperienceRangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidExperienceRange {
    String message() default "Опыт 'от' не может быть больше, чем 'до'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}