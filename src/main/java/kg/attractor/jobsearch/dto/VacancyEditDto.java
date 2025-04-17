package kg.attractor.jobsearch.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import kg.attractor.jobsearch.util.ValidExperienceRange;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ValidExperienceRange
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
