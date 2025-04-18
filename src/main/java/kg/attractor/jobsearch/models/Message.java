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
@Table(name = "messages")
public class Message {
    @JoinColumn(name = "responded_applicant_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private RespondedApplicant respondedApplicantsId;
    @Lob
    private String content;

    private LocalDateTime timestamp;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
