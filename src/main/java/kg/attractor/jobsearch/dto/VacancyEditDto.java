package kg.attractor.jobsearch.dto;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VacancyEditDto {
    @Size(min = 3, max = 30, message = "имя должно содержать от 1 - 30 символов")
    @Pattern(regexp = ".*\\p{L}.*", message = "Название не может состоять только из цифр")
    private String name;
    private String description;
    private Long categoryId;
    private Double salary;
    private Integer expFrom;
    private Integer expTo;
    private Boolean isActive;
    @PastOrPresent
    private LocalDateTime createdDate;
    @PastOrPresent
    private LocalDateTime updateTime;
}
