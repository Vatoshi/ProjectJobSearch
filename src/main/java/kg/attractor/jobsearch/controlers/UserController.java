package kg.attractor.jobsearch.controlers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    @PostMapping("create")
    public void createUser() {

    }

    @PostMapping("{userId}/create")
    public ResponseEntity createСontent() {
        // принимать dto от типа
        // в зависимости от типа аккаунта создавать вакансию либо резюме
        return ResponseEntity.created(null).build();
    }

    @PostMapping("{userId}/edit/{id}")
    public ResponseEntity editContent() {
        // осуществлять поиск для редактирования резюме или вакансию по типу аккаунта
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{userId}/delete/{id}")
    public ResponseEntity deleteContent(@PathVariable long userId) {
        // искать резюме либо вакансию по типу аккаунта и удалить его
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{userId}/resumes")
    public HttpStatus getAllResumes() {
        // проверить является ли пользователь работодателем и показать ему имеющиеся резюме
        return HttpStatus.OK;
    }

    @GetMapping("{userId}/resumes/{categoryId.name}")
    public HttpStatus getAllResumesByCategory() {
        // проверить является ли пользователь работодателем и показать ему имеющиеся резюме
        return HttpStatus.OK;
    }

    @GetMapping("{userId}/vacancies")
    public HttpStatus getAllVacancies() {
        // также проверить ищет ли пользователь работу и показать ему вакансии по указанной категории
        return HttpStatus.OK;
    }

    @GetMapping("{userId}/vacancies/{categoryId.name}")
    public HttpStatus getAllVacanciesByCategory() {
        // проверить является ли пользователь работодателем и показать ему имеющиеся резюме по указанной категории
        return HttpStatus.OK;
    }

    @GetMapping("vacancies/")
    public HttpStatus getApplyVacancies() {

        return HttpStatus.OK;
    }
}


