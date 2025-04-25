package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.Resume;
import kg.attractor.jobsearch.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    @Query("select r from Resume r where r.category.name = :name")
    List<Resume> findActiveByCategoryName(@Param("categoryName") String categoryName);

    List<Resume> findByIsActiveTrue(Pageable pageable);

    Page<Resume> getResumesByUserId(Long userId, Pageable pageable);

    @Query("select count(r) > 0 from Resume r where r.id = :resumeId and r.user.id = :userId")
    boolean existsByResumeIdAndUserId(@Param("resumeId") Long resumeId, @Param("userId") Long userId);

    @Query("select count(r) from Resume r where r.isActive = true")
    int getResumesCount();

    @Query("select count(r) from Resume r where r.user.id = :id")
    int getResumesCountNonActive(Long id);

    Long user(User user);
}
