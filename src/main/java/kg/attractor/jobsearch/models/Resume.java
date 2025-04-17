package kg.attractor.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume {
    @JoinColumn(name = "applicant_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String name;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private Double salary;

    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "created_date")
    private LocalDate createdDate;
    @Column(name = "update_time")
    private LocalDate updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<WorkExperienceInfo> workExperienceInfo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<EducationInfo> educationInfo;
}
