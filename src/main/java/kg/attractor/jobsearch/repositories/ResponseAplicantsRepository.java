package kg.attractor.jobsearch.repositories;

import kg.attractor.jobsearch.models.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ResponseAplicantsRepository extends JpaRepository<RespondedApplicant, Long> {

    @Query("select count(r) from RespondedApplicant r where r.vacancy.id = :vacancyId")
    Integer getRespondedCount(Long vacancyId);
}
