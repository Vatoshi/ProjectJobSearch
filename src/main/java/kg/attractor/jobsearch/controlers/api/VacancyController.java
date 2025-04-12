package kg.attractor.jobsearch.controlers.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.dto.VacancyEditDto;
import kg.attractor.jobsearch.dto.mutal.VacancyForWebDto;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.servise.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacancy")
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping
    public List<VacancyForWebDto> getAllVacancies() {
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
    public ResponseEntity<VacancyEditDto> editVacancy(@PathVariable Long resumeId, @RequestBody VacancyEditDto vacancyDto) {
        return vacancyService.updateResume(resumeId, vacancyDto);
    }

    @DeleteMapping("delete/{vacancyId}")
    public HttpStatus deleteVacancy(@PathVariable Long vacancyId) throws NotFound {
        return vacancyService.deleteVacancy(vacancyId);
    }
}
