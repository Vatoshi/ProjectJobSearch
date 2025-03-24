package kg.attractor.jobsearch.controlers;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.models.Vacancy;
import kg.attractor.jobsearch.servise.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacancy")
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public List<VacancyDto> getAllVacancies() {
        return vacancyService.getAllVacancies();
    }

    @GetMapping("category/{category}")
    public List<VacancyDto> getVacanciesByCategory(@PathVariable  String category) {
        return vacancyService.getVacancyByCategory(category);
    }

    @GetMapping("responded/{userId}")
    public List<VacancyDto> getVacancyById(@PathVariable  Long userId) {
        return vacancyService.getApplicantsForVacancy(userId);
    }

    @GetMapping("vacancy-responded/{vacancyId}")
    public List<UserDto> getRespondedUsers(@PathVariable Long vacancyId) {
        return vacancyService.getResponedUsers(vacancyId);
    }

}
