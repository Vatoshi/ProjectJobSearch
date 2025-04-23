package kg.attractor.jobsearch.servise.helpers;

import kg.attractor.jobsearch.models.Vacancy;
import kg.attractor.jobsearch.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserVacancyServise {
    private final VacancyRepository vacancyRepository;

    public List<Vacancy> getVacanciesByUserId(Long userId, Pageable pageable){
        return vacancyRepository.getVacanciesByUserId(userId, pageable);
    }

    public Integer getVacancyCountNotActive(Long userId) {
        return vacancyRepository.getVacancyCountNotActive(userId);
    }

}
