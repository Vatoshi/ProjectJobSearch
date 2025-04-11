package kg.attractor.jobsearch.controlers;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserFormDto;
import kg.attractor.jobsearch.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final UserService userService;

    @GetMapping("login")
    public String getAuthPage() {
        return "login";
    }

    @GetMapping("register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("register")
    public String postRegister(@ModelAttribute @Valid UserFormDto userFormDto) {
        userService.createAcc(userFormDto);

        return "redirect:/auth/login";
    }
}
