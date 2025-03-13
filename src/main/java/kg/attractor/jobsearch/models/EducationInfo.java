package kg.attractor.jobsearch.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationInfo {
    private int resumeId;
    private String institution;
    private String program;
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;
    private int id;
}
