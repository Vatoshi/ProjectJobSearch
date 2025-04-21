package kg.attractor.jobsearch.dto.mutal;

import jakarta.persistence.*;
import kg.attractor.jobsearch.models.Resume;
import kg.attractor.jobsearch.models.Role;
import kg.attractor.jobsearch.models.Vacancy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String avatar;
    private Boolean enabled;
    private Role role;
    private List<Vacancy> vacancies;
    private List<Resume> resumes;
}
