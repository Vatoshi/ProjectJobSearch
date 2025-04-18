package kg.attractor.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "responded_applicants")
public class RespondedApplicant {
    @JoinColumn(name = "resume_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @JoinColumn(name = "vacancy_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vacancy vacancy;

    private Boolean confirmation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
