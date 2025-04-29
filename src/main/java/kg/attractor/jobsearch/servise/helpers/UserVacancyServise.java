package kg.attractor.jobsearch.servise.helpers;

import kg.attractor.jobsearch.dto.mutal.VacancyForProfile;
import kg.attractor.jobsearch.models.Vacancy;
import kg.attractor.jobsearch.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserVacancyServise {
    private final VacancyRepository vacancyRepository;

    public Page<VacancyForProfile> getVacanciesByUserId(Long userId, Pageable pageable) {
        return vacancyRepository.getVacanciesByUserId(userId, pageable)
                .map(vacancy -> VacancyForProfile.builder()
                        .updateTime(Date.from(vacancy.getUpdateTime().atZone(ZoneId.systemDefault()).toInstant()))
                        .name(vacancy.getName())
                        .id(vacancy.getId())
                        .build());
    }
}
