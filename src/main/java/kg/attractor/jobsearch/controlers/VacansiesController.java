package kg.attractor.jobsearch.controlers;

import kg.attractor.jobsearch.repositories.UserRepository;
import kg.attractor.jobsearch.servise.UserService;
import kg.attractor.jobsearch.servise.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
@RequestMapping("vacancies")
public class VacansiesController {
    private final VacancyService vacancyService;
    private final UserRepository userRepository;

    @GetMapping
    public String getMainPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            model.addAttribute("user", userRepository.findByEmail(username));
        } else {
            model.addAttribute("user", null);
        }
        // использовать jpa там все равно обработка null
        model.addAttribute("vacancies", vacancyService.getAllVacancies());
        return "main/vacancies";
    }

    @GetMapping("details")
    public String getVacancy(@RequestParam("id") Long id,  Model model, Authentication auth) {
        model.addAttribute("vacancy", vacancyService.getAllVacancies()
                .stream()
                .filter(v -> Objects.equals(v.getId(), id))
                .findFirst()
                .orElse(null));
        return "main/vacancy-details";
    }
}
