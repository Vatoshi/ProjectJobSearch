package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.repositories.ResponseAplicantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ResponseAplicantsServise {
    private final ResponseAplicantsRepository responseAplicantsRepository;

    public Integer getCountResponseToVacancy(Long vacancyId) {
        return responseAplicantsRepository.getRespondedCount(vacancyId);
    }
}
