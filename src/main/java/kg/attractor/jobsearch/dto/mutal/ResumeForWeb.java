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
    private Integer categoryId;
    private Double salary;
    private boolean isActive;
    private LocalDate updateTime;
}
