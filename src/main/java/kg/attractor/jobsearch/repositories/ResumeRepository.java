package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    @Query("select r from Resume r where r.category.name = :name")
    List<Resume> findActiveByCategoryName(@Param("categoryName") String categoryName);

    List<Resume> findByIsActiveTrue();

    List<Resume> getResumesByUserId(Long userId);

    @Query("select count(r) > 0 from Resume r where r.id = :resumeId and r.user.id = :userId")
    boolean existsByResumeIdAndUserId(@Param("resumeId") Long resumeId, @Param("userId") Long userId);

}
