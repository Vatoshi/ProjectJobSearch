package kg.attractor.jobsearch.controlers;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserFormDto;
import kg.attractor.jobsearch.servise.mainServises.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверная почта или пароль");
        }
        return "/login/login";
    }

    @GetMapping("register")
    public String getRegisterPage(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());
        return "login/register";
    }

    @PostMapping("register")
    public String postRegister(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userFormDto", userFormDto);
            model.addAttribute("accountType","Укажите тип аккаунта");
            return "login/register";
        }
        if (userService.existEmail(userFormDto.getEmail())) {
            bindingResult.rejectValue("email","error.email", "Пользователь с такой почтой уже существует");
            model.addAttribute("userFormDto", userFormDto);
            return "login/register";
        }
        userService.createAcc(userFormDto);
        return "redirect:/auth/login";
    }
}
