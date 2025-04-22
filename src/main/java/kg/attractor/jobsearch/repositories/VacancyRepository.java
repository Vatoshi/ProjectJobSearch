package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.Vacancy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("select v from Vacancy v where v.user.id = :id and v.id = :id")
    void exist (@Param("userId") Long userId, @Param("id") Long id);

    @Query("select v from Vacancy v where v.isActive = true")
    List<Vacancy> findActiveVacancies(Pageable pageable);

    @Query("select count(v) from Vacancy v where v.isActive = true")
    Integer getVacancyCount();

    @Query("select count(v) from Vacancy v where v.user.id = :id")
    Integer getVacancyCountNotActive(Long id);

    List<Vacancy> getVacanciesByUserId(Long userId, Pageable pageable);

    Optional<Vacancy> getVacancyById(Long vacancyId);
}
