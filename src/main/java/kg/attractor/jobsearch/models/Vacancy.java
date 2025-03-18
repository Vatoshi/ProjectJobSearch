package kg.attractor.jobsearch.models;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Vacancy {
    private String name;
    private String description;
    private Long categoryId;
    private double salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private int authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedTime;
    private Long id;
}
