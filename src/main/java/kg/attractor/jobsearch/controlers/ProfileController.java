package kg.attractor.jobsearch.controlers;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.servise.ResumeService;
import kg.attractor.jobsearch.servise.UserService;
import kg.attractor.jobsearch.servise.VacancyService;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile(Model model, Authentication auth) {
        model.addAttribute("user", userService.getEmail(auth.getName()));
        model.addAttribute("resumes", resumeService.getUserResume(auth.getName()));
        model.addAttribute("vacancies", vacancyService.getVacancyByUser(auth.getName()));
        model.addAttribute("userEditDto", new UserEditDto());
        return "profile";
    }

    @GetMapping("/response")
    public String profileResponse (Model model, Authentication auth) {
        model.addAttribute("user", userService.getEmail(auth.getName()));
        model.addAttribute("userEditDto", new UserEditDto());
        return "profile-response";
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

    @GetMapping("create-resume")
    public String showResumeForm(Authentication auth, Model model) {
        model.addAttribute("user", userService.getEmail(auth.getName()));
        model.addAttribute("resumeDto",new ResumeDto());
        return "resume-form";
    }

    @PostMapping("create-resume")
    public String createResume(
            @Valid @ModelAttribute ResumeDto resumeDto,
            BindingResult bindingResult,
            Authentication auth,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getEmail(auth.getName()));
            return "resume-form";
        }
        resumeService.createResume(resumeDto);
        return "redirect:/profile";
    }
}
