package kg.attractor.jobsearch.controlers;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.*;
import kg.attractor.jobsearch.dto.mutal.UserProfile;
import kg.attractor.jobsearch.servise.helpers.UserResumeServise;
import kg.attractor.jobsearch.servise.helpers.UserVacancyServise;
import kg.attractor.jobsearch.servise.mainServises.ResumeService;
import kg.attractor.jobsearch.servise.mainServises.UserService;
import kg.attractor.jobsearch.servise.mainServises.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
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
    private final UserResumeServise userResumeServise;
    private final UserVacancyServise userVacancyServise;

    @GetMapping
    public String profile(Model model, Authentication auth,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("logout","logout");
        model.addAttribute("currentPage", page);
        UserProfile user = userService.getUserForProfile(auth.getName(),pageable);
        model.addAttribute("user", user);
        model.addAttribute("resumes", userResumeServise.getResumesByUserId(user.getUserId(), pageable));
        model.addAttribute("vacancies", userVacancyServise.getVacanciesByUserId(user.getUserId(),pageable));

        model.addAttribute("userEditDto", new UserEditDto());
        return "profile/profile";
    }

    @GetMapping("/response")
    public String profileResponse (Model model, Authentication auth) {
        model.addAttribute("user", userService.getUserByEmail(auth.getName()));
        model.addAttribute("userEditDto", new UserEditDto());
        return "profile/profile-response";
    }

    @GetMapping("/edit")
    public String editUserProfile(Model model, Authentication auth) {
        model.addAttribute("user", userService.getUserByEmail(auth.getName()));
        model.addAttribute("userEditDto", userService.getUserByEmail(auth.getName()));
        return "forms/profile-edit";
    }

    @PostMapping("/edit")
    public String editUserProfile(@Valid @ModelAttribute("userEditDto") UserEditDto userEditDto, BindingResult bindingResult, Authentication auth, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getUserByEmail(auth.getName()));
            model.addAttribute("userEditDto", userEditDto);
            return "forms/profile-edit";
        }
        userService.editAcc(userEditDto, userService.userId(auth.getName()));
        return "redirect:/profile";
    }

    @PostMapping("avatar")
    public String addAvatar(MultipartFile image, Authentication auth) {
        userService.saveImage(image,auth.getName());
        return "redirect:/profile";
    }

    @GetMapping("create-resume")
    public String showResumeForm(Authentication auth, Model model) {
        model.addAttribute("user", userService.getUserByEmail(auth.getName()));
        model.addAttribute("resumeDto",new ResumeDto());
        return "forms/resume-form";
    }

    @PostMapping("create-resume")
    public String createResume(
            @Valid ResumeDto resumeDto,
            BindingResult bindingResult,
            Authentication auth,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getUserByEmail(auth.getName()));
            model.addAttribute("errorResume", "Выберите категорию");
            return "forms/resume-form";
        }
        resumeService.createResume(resumeDto, auth.getName());
        return "redirect:/profile";
    }

    @GetMapping("edit-resume")
    public String editResumeForm(Model model, Authentication auth, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUserByEmail(auth.getName()));
        model.addAttribute("resumeDto", resumeService.getResumeByUserIdAndId(id,auth.getName()));
        return "forms/resume-edit";
    }

    @PostMapping("edit-resume")
    public String editResume(Authentication auth, ResumeDto resumeDto,BindingResult bindingResult, Model model, @RequestParam("id") Long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getUserByEmail(auth.getName()));
            return "forms/resume-edit";
        }
        resumeService.updateResume(id,resumeDto);
        return "redirect:/profile";
    }

    @GetMapping("update-resume-time")
    public String updateResumeTime(@RequestParam("id") Long id) {
        resumeService.updateTime(id);
        return "redirect:/profile";
    }

    @GetMapping("view-resume")
    public String resumesDetails(@RequestParam("id") Long id, Model model, Authentication auth) {
        model.addAttribute("user", userService.getUserByEmail(auth.getName()));
        model.addAttribute("resume", resumeService.getResumeByUserIdAndId(id,auth.getName()));
        return "main/resume-details";
    }

    @GetMapping("create-vacancy")
    public String showVacancyForm(Authentication auth, Model model) {
        model.addAttribute("user", userService.getUserByEmail(auth.getName()));
        model.addAttribute("vacancyDto",new VacancyDto());
        return "forms/vacancy-form";
    }

    @PostMapping("create-vacancy")
    public String createVacancy(@Valid @ModelAttribute VacancyDto vacancyDto,
                                BindingResult bindingResult,
                                Authentication auth,
                                Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getUserByEmail(auth.getName()));
            model.addAttribute("vacancyDto", vacancyDto);
            model.addAttribute("errorVacancy", "Выберите категорию");
            return "forms/vacancy-form";
        }
        if (vacancyDto.getExpFrom() > vacancyDto.getExpTo()) {
            bindingResult.rejectValue("expFrom", "error.expFrom","От не может быть больше чем до");
        }
        vacancyService.createVacancy(vacancyDto, auth.getName());
        return "redirect:/profile";
    }

    @GetMapping("edit-vacancy")
    public String editVacancyForm(Model model,Authentication auth, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUserByEmail(auth.getName()));
        model.addAttribute("vacancyEditDto", vacancyService.getVacancyById(id,auth.getName()));
        return "forms/vacancy-edit";
    }

    @PostMapping("edit-vacancy")
    public String editVacancy(VacancyEditDto vacancyEditDto,
                              BindingResult bindingResult,
                              Model model,
                              Authentication auth,
                              @RequestParam("id") Long id) {
        if (vacancyEditDto.getExpFrom() > vacancyEditDto.getExpTo()) {
            bindingResult.rejectValue("expFrom", "error.expFrom","От не может быть больше чем до");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getUserByEmail(auth.getName()));
            model.addAttribute("vacancyDto", vacancyEditDto);
            return "forms/vacancy-edit";
        }
        vacancyService.updateVacancy(id,vacancyEditDto);
        return "redirect:/profile";
    }

    @GetMapping("update-vacancy-time")
    public String updateVacancyTime(@RequestParam("id") Long id) {
        vacancyService.updateTime(id);
        return "redirect:/profile";
    }

    @GetMapping("view-vacancy")
    public String viewVacancy(@RequestParam("id") Long id, Model model, Authentication auth) {
        model.addAttribute("user", userService.getUserByEmail(auth.getName()));
        model.addAttribute("vacancy", vacancyService.getVacancyByIdForProfile(id,auth.getName()));
    return "main/vacancy-details";
    }
}
