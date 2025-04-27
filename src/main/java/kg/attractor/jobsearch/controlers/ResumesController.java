package kg.attractor.jobsearch.controlers;

import kg.attractor.jobsearch.repositories.UserRepository;
import kg.attractor.jobsearch.servise.mainServises.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("resumes")
public class ResumesController {
    private final ResumeService resumeService;
    private final UserRepository userRepository;

    @GetMapping()
    public String getResumes(Model model, Authentication auth,
                             @RequestParam(defaultValue = "5") int size,
                             @RequestParam(defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, size);
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            String username = auth.getName();
            model.addAttribute("user", userRepository.findByEmail(username));
        } else {
            model.addAttribute("user", null);
        }
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("resumes", resumeService.getResumes(pageable));
        return "main/resumes";
    }
}
