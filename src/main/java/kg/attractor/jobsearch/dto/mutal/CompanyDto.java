package kg.attractor.jobsearch.dto.mutal;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.models.Vacancy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String avatar;
    private List<Vacancy> vacancies;
}
