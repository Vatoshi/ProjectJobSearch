package kg.attractor.jobsearch.controlers;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.Vacancy;
import kg.attractor.jobsearch.servise.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
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

    @PostMapping("create")
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody @Valid VacancyDto vacancyDto) {
        return vacancyService.createVacancy(vacancyDto);
    }

    @PostMapping("edit/{resumeId}")
    public ResponseEntity<VacancyDto> editVacancy(@PathVariable Long resumeId, @RequestBody VacancyDto vacancyDto) {
        return vacancyService.updateResume(resumeId, vacancyDto);
    }

    @DeleteMapping("delete/{vacancyId}")
    public HttpStatus deleteVacancy(@PathVariable Long vacancyId) throws NotFound {
        return vacancyService.deleteVacancy(vacancyId);
    }
}
