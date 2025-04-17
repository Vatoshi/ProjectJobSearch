package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("select v from Vacancy v where v.user.id = :id and v.id = :id")
    void exist (@Param("userId") Long userId, @Param("id") Long id);

    @Query("select v from Vacancy v where v.isActive = true")
    List<Vacancy> findActiveVacancies();
}
