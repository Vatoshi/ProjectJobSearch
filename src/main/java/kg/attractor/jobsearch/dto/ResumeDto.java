package kg.attractor.jobsearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    @NotNull(message = "Имя не должно быть пустым")
    @Size(min = 1, max = 30, message = "название от 1 до 30")
    @Pattern(regexp = ".*\\p{L}.*", message = "Название не может состоять только из цифр")
    private String name;
    private Integer categoryId;
    @PositiveOrZero
    private Double salary;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isActive;
    @PastOrPresent
    private LocalDateTime createdDate;
    @PastOrPresent
    private LocalDateTime updateTime;
    List<WorkExperienceInfoDto> workExperienceInfo = new ArrayList<>();
    List<EducationInfoDto> educationInfo = new ArrayList<>();
}
