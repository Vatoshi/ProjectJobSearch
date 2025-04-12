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
public class VacancyForWebDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Double salary;
    private Integer ExpFrom;
    private Integer ExpTo;
    private String author;
    private Boolean isActive;
    private LocalDate updateTime;
}
