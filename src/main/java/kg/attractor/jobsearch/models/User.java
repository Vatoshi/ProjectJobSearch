package kg.attractor.jobsearch.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String avatar;
    private Boolean enabled;
    @JoinColumn(name = "role_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Vacancy> vacancies;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Resume> resumes;
}
