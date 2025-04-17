package kg.attractor.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Vacancy> vacancies;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Resume> resumes;
}
