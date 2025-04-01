package kg.attractor.jobsearch.models;

import lombok.Data;

@Data
public class RespondedApplicant {
    private Long resumeId;
    private Long vacancyId;
    private Boolean confirmation;
    private Long id;
}
