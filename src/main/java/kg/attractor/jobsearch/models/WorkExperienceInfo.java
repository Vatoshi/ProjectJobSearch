package kg.attractor.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "work_experience_info")
public class WorkExperienceInfo {
    @JoinColumn(name = "resume_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    private Integer years;

    @Column(name = "company_name")
    private String companyName;
    private String position;
    private String responsibilities;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
