package kg.attractor.jobsearch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResumeDto {
    private String name;
    private int categoryId;
    private double salary;
    private boolean isActive;
}
