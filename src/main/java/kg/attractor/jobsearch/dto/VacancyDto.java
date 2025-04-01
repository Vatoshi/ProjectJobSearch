package kg.attractor.jobsearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class VacancyDto {
    @NotNull(message = "имя не должно быть пустым")
    @Size(min = 3, max = 30, message = "имя должно содержать от 1 - 30 символов")
    @Pattern(regexp = ".*\\p{L}.*", message = "Название не может состоять только из цифр")
    private String name;
    private String description;
    private Long categoryId;
    @PositiveOrZero
    private Double salary;
    @PositiveOrZero
    private Integer expFrom;
    @PositiveOrZero
    private Integer expTo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isActive;
    @PastOrPresent
    private LocalDateTime createdDate;
    @PastOrPresent
    private LocalDateTime updateTime;
}
