package kg.attractor.jobsearch.dto.mutal;

import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDetailsDto {
    private String name;
    private Long categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;
    List<WorkExperienceInfoDto> workExperienceInfo;
    List<EducationInfoDto> educationInfo;
    private String author;
}
