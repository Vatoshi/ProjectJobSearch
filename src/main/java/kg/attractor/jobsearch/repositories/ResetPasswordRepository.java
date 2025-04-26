package kg.attractor.jobsearch.repositories;


import kg.attractor.jobsearch.models.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword, Long> {

    boolean existsByEmailAndToken(String email, String token);

    boolean existsByEmail(String email);

    void deleteByEmail(String email);

    ResetPassword findByEmail(String email);
}
