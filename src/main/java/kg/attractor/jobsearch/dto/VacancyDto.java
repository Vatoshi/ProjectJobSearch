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
    @NotNull
    @Size(min = 3, max = 30, message = "{valid.130}")
    @Pattern(regexp = ".*\\p{L}.*", message = "{valid.withoutc}")
    private String name;
    @Size(min = 5)
    private String description;
    private Long categoryId;
    @PositiveOrZero
    private Double salary;
    @PositiveOrZero
    @NotNull(message = "{valid.from}")
    private Integer expFrom;
    @PositiveOrZero
    @NotNull(message = "{valid.to}")
    @Max(value = 50, message = "{valid.50}")
    private Integer expTo;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isActive;
    @PastOrPresent
    private LocalDateTime createdDate;
    @PastOrPresent
    private LocalDateTime updateTime;
}
