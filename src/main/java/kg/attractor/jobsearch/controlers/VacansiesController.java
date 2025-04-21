package kg.attractor.jobsearch.controlers;

import kg.attractor.jobsearch.repositories.UserRepository;
import kg.attractor.jobsearch.servise.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("vacancies")
public class VacansiesController {
    private final VacancyService vacancyService;
    private final UserRepository userRepository;

    @GetMapping
    public String getMainPage(Model model , @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "2") int size,
                                            @RequestParam(defaultValue = "responses") String order) {

        Pageable pageable = PageRequest.of(page, size);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            model.addAttribute("user", userRepository.findByEmail(username));
        } else {
            model.addAttribute("user", null);
        }
        model.addAttribute("order", order);
        model.addAttribute("totalPages",vacancyService.getTotalPages(size));
        model.addAttribute("currentPage", pageable.getPageNumber());
        if (order.equals("responses")) {
        model.addAttribute("vacancies", vacancyService.getAllVacancies(pageable));}
        if (order.equals("updateTime")) {
            pageable = PageRequest.of(page, size, Sort.by("updateTime").descending());
            model.addAttribute("vacancies", vacancyService.getAllVacancies(pageable));}
        return "main/vacancies";
    }

    @GetMapping("details")
    public String getVacancy(@RequestParam("id") Long id,  Model model, Authentication auth) {
        model.addAttribute("vacancy", vacancyService.getVacancyById(id).orElse(null));
        return "main/vacancy-details";
    }
}
