package kg.attractor.jobsearch.controlers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    @PostMapping("create")
    public void createUser() {

    }

    @PostMapping("{userId}/create")
    public void createСontent() {
        // в зависимости от типа аккаунта создавать вакансию либо резюме
    }

    @PostMapping("{userId}/edit/{id}")
    public void editContent() {
        // осуществлять поиск для редактирования резюме или вакансию по типу аккаунта
    }

    @DeleteMapping("{userId/delete/{id}")
    public void deleteContent() {
        // осуществлять удаление резюме или вакансию по типу аккаунта
    }

    @GetMapping("resumes")
    public void getAllResumes() {

    }

    public void getAllVacancies() {
    }

}


