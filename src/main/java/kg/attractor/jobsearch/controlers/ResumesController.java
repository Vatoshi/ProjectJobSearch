package kg.attractor.jobsearch.controlers;

import kg.attractor.jobsearch.servise.ResumeService;
import kg.attractor.jobsearch.servise.UserService;
import kg.attractor.jobsearch.servise.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("resumes")
public class ResumesController {
    private final UserService userService;
    private final ResumeService resumeService;

    @GetMapping()
    public String getResumes(Model model, Authentication auth) {
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            model.addAttribute("user", userService.getEmail(username));
        } else {
            model.addAttribute("user", null);
        }
        model.addAttribute("resumes", resumeService.getResumes());
        return "resumes";
    }
}
