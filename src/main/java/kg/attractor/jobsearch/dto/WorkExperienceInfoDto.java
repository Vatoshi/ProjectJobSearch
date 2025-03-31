package kg.attractor.jobsearch.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperienceInfoDto {
    private Integer years;
    private String companyName;
    private String position;
    private String responsibilities;
}
