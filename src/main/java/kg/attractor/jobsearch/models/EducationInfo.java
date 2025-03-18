package kg.attractor.jobsearch.models;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EducationInfo {
    private Long resumeId;
    private String institution;
    private String program;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String degree;
    private Long id;
}
