package kg.attractor.jobsearch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resume {
    private Long applicantId;
    private String name;
    private Integer categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;
    private Long id;
}
