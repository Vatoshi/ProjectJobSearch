package kg.attractor.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts_info")
public class ContactInfo {
    @JoinColumn(name = "type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ContactType typeId;

    @JoinColumn(name = "resume_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resumeId;

    @Column(name = "value_")
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
