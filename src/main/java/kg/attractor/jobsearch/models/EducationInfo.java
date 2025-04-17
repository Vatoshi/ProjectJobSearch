package kg.attractor.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "education_info")
public class EducationInfo {
    @JoinColumn(name = "resume_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    private String institution;
    private String program;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    private String degree;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
