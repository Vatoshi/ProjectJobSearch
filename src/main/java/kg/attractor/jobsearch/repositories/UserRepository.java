package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

    boolean existsByEmail(String email);

    Page<User> getUsersByRoleId(Pageable pageable, Long roleId);
}
