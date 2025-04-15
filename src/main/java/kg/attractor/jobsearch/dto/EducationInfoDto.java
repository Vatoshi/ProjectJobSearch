package kg.attractor.jobsearch.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationInfoDto {
    private String institution;
    private String program;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime endDate;
    private String degree;
}
