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
    @Past(message = "укажите действительную дату")
    private LocalDate startDate;
    @Past(message = "укажите действительную дату")
    private LocalDate endDate;
    private String degree;
}
