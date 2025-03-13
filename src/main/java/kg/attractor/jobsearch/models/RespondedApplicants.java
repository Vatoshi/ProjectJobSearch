package kg.attractor.jobsearch.models;

import lombok.Data;

@Data
public class RespondedApplicants {
    private int resumeId;
    private int vacancyId;
    private boolean confirmation;
    private int id;
}
