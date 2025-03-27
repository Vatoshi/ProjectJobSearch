package kg.attractor.jobsearch.dto;

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
    private String name;
    private int categoryId;
    @NotNull
    @Min(value = 10,message = "не должно быть пустным")
    private double salary;
    @AssertTrue
    private boolean isActive;
    @PastOrPresent
    private LocalDateTime createdDate;
    @PastOrPresent
    private LocalDateTime updateTime;
}
