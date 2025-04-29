package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    boolean existsByUserIdAndId(Long userId, Long id);

    @Query("select v from Vacancy v where v.isActive = true")
    Page<Vacancy> findActiveVacancies(Pageable pageable);

    @Query("select count(v) from Vacancy v where v.isActive = true")
    Integer getVacancyCount();

    @Query("select count(v) from Vacancy v where v.user.id = :id")
    Integer getVacancyCountNotActive(Long id);

    Page<Vacancy> getVacanciesByUserId(Long userId, Pageable pageable);

    Optional<Vacancy> getVacancyById(Long vacancyId);
}
