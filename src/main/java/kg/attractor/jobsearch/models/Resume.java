package kg.attractor.jobsearch.models;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Resume {
    private Long applicantId;
    private String name;
    private int categoryId;
    private double salary;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime updatedTime;
    private Long id;
}
