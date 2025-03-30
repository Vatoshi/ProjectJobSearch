package kg.attractor.jobsearch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {
    private String name;
    private String description;
    private Long categoryId;
    private double salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private Long id;
}
