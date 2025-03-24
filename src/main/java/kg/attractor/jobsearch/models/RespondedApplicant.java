package kg.attractor.jobsearch.models;

import lombok.Data;

@Data
public class RespondedApplicant {
    private Long resumeId;
    private Long vacancyId;
    private boolean confirmation;
    private Long id;
}
