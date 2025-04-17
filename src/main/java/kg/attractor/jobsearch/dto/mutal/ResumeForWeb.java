package kg.attractor.jobsearch.dto.mutal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForWeb {
    private String author;
    private String name;
    private Long categoryId;
    private Double salary;
    private Boolean isActive;
    private LocalDate updateTime;
}
