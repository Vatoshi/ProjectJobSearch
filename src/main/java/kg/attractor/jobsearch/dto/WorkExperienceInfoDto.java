package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceInfoDto {
    @Min(value = 0)
    @Max(value = 50)
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
