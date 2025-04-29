package kg.attractor.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;
    @Column(name = "salary")
    private Double salary;
    @Column(name = "exp_from")
    private Integer expFrom;
    @Column(name = "exp_to")
    private Integer expTo;
    @Column(name = "is_active")
    private Boolean isActive;
    @JoinColumn(name = "author_id")
    @ManyToOne
    private User user;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vacancy")
    private List<RespondedApplicant> respondedApplicants;
}
