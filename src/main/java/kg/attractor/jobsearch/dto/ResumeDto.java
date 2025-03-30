package kg.attractor.jobsearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResumeDto {
    @NotNull(message = "Имя не должно быть пустым")
    @Size(min = 1, max = 30, message = "название от 1 до 30")
    @Pattern(regexp = ".*\\p{L}.*", message = "Название не может состоять только из цифр")
    private String name;
    private Integer categoryId;
    @PositiveOrZero
    private Double salary;
    private boolean isActive;
    @JsonIgnore
    @PastOrPresent
    private LocalDateTime createdDate;
    @JsonIgnore
    @PastOrPresent
    private LocalDateTime updateTime;
}
