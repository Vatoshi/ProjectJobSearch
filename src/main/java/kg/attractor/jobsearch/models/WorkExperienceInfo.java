package kg.attractor.jobsearch.models;

import lombok.Data;

@Data
public class WorkExperienceInfo {
    private int resumeId;
    private int years;
    private String companyName;
    private String position;
    private String responsibilities;
    private int id;
}
