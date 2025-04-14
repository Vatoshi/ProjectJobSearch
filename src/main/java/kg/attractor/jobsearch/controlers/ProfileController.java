package kg.attractor.jobsearch.controlers;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.servise.ResumeService;
import kg.attractor.jobsearch.servise.UserService;
import kg.attractor.jobsearch.servise.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("user", userService.getEmail(username));
        model.addAttribute("resumes", resumeService.getUserResume(username));
        model.addAttribute("vacancies", vacancyService.getVacancyByUser(username));
        model.addAttribute("userEditDto", new UserEditDto());
        return "profile";
    }

    @GetMapping("/edit")
    public String editUserProfile(Model model, Authentication authentication) {
        model.addAttribute("user", userService.getEmail(authentication.getName()));
        model.addAttribute("userEditDto", new UserEditDto());
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String editUserProfile(@Valid @ModelAttribute("userEditDto") UserEditDto userEditDto, BindingResult bindingResult, Authentication authentication, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getEmail(authentication.getName()));
            model.addAttribute("userEditDto", userEditDto);
            return "profile-edit";
        }
        userService.editAcc(userEditDto, userService.userId(authentication.getName()));
        return "redirect:/profile";
    }

    @PostMapping("avatar")
    public String addAvatar(MultipartFile image, Authentication auth) {
        userService.saveImage(image,auth.getName());
        return "redirect:/profile";
    }
}
