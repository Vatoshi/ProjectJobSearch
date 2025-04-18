package kg.attractor.jobsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    @NotNull(message = "имя не должно быть пустым")
    @Size(min = 3, max = 30, message = "имя должно содержать от 1 - 30 символов")
    @Pattern(regexp = ".*\\p{L}.*", message = "Название не может состоять только из цифр")
    private String name;
    @Size(min = 5, message = "минимум 5 символов")
    private String description;
    private Long categoryId;
    @PositiveOrZero
    private Double salary;
    @PositiveOrZero
    private Integer expFrom;
    @PositiveOrZero
    @Max(value = 50, message = "Максимум 50 лет")
    private Integer expTo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isActive;
    @PastOrPresent
    private LocalDateTime createdDate;
    @PastOrPresent
    private LocalDateTime updateTime;
}
