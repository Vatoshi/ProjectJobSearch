package kg.attractor.jobsearch.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    @NotNull
    @Size(min = 1, max = 30)
    @Pattern(regexp = ".*\\p{L}.*", message = "{valid.withoutc}")
    private String name;
    private Long categoryId;
    @PositiveOrZero
    private Double salary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isActive;
    @PastOrPresent
    private LocalDate createdDate;
    @PastOrPresent
    private LocalDate updateTime;
    @Valid
    List<WorkExperienceInfoDto> workExperienceInfo;
    @Valid
    List<EducationInfoDto> educationInfo;
}
