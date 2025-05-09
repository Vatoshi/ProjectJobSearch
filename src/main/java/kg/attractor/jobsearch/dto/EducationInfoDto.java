package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.Past;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfoDto {
    private String institution;
    private String program;
    @Past(message = "{valid.realdata}")
    private LocalDate startDate;
    @Past(message = "{valid.realdata}")
    private LocalDate endDate;
    private String degree;
}
