package kg.attractor.jobsearch.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.jobsearch.dto.VacancyDto;

public class ExperienceRangeValidator implements ConstraintValidator<ValidExperienceRange, VacancyDto> {

    @Override
    public boolean isValid(VacancyDto dto, ConstraintValidatorContext context) {
        Integer from = dto.getExpFrom();
        Integer to = dto.getExpTo();
        if (from == null || to == null) {
            return true;
        }
        return from <= to;
    }
}
