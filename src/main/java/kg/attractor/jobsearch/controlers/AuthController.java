package kg.attractor.jobsearch.controlers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserFormDto;
import kg.attractor.jobsearch.dto.mutal.passwordChangeDto;
import kg.attractor.jobsearch.servise.EmailService;
import kg.attractor.jobsearch.servise.ResetPasswordServise;
import kg.attractor.jobsearch.servise.mainServises.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final UserService userService;
    private final ResetPasswordServise resetPasswordServise;
    private final EmailService emailService;

    @GetMapping("/403")
    public String forbidden(Model model, HttpServletRequest request) {
        model.addAttribute("status",403);
        model.addAttribute("reason", HttpStatus.FORBIDDEN.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }

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

    @GetMapping("reset")
    public String resetPassword(String email, Model model) {
        model.addAttribute("email", email != null ? email : "");
        return "/login/reset";
    }

    @PostMapping("reset")
    public String resetPasswordPost(String email, Model model, HttpServletRequest request) {
        model.addAttribute("email", email);
        if (!userService.existEmail(email)) {
            model.addAttribute("error","Почта не найдена");
            return "login/reset";
        }
        request.getSession().setAttribute("userEmail", email);
        resetPasswordServise.createToken(email);
        emailService.sendEmail(email,resetPasswordServise.getTokenByEmail(email));
        return "redirect:/auth/reset-code";
    }

    @GetMapping("reset-code")
    public String resetCode(HttpServletRequest request, Model model) {
        String email = (String) request.getSession().getAttribute("userEmail");
        model.addAttribute("email", email);
                if (!resetPasswordServise.checkExistToken(email)) {
                    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                    if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
                        return "redirect:/profile/edit-password";
                    } else {
                        return "redirect:/auth/login";
                    }
        }
        return "/login/reset-code";
    }

    @PostMapping("reset-code")
    public String resetCodePost(String fullCode, Model model, HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("userEmail");
        model.addAttribute("fullCode", fullCode);
            if (email == null) {
                return "redirect:/auth/reset";
            }

            if (!resetPasswordServise.checkToken(email, fullCode)) {
            model.addAttribute("invalid", "Неправильный код");
            model.addAttribute("email", email);
            return "/login/reset-code";
        }
        return "redirect:/auth/change-form";
    }

    @GetMapping("change-form")
    public String changeForm(Model model) {
        // type password не дает сохранять веденные значения (spring?) ???
        model.addAttribute("passwordChangeDto", new passwordChangeDto());
        return "/login/new-password";
    }

    @PostMapping("change-form")
    @Transactional
    public String changeFormPost(@Valid passwordChangeDto pas,
                                 BindingResult bindingResult,
                                 Model model,
                                 HttpServletRequest request)
    {
        model.addAttribute("passwordChangeDto", pas);
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "/login/new-password";
        }
        if (!pas.getNewPassword().equals(pas.getConfirmPassword())) {
            model.addAttribute("error", "пароли не совпадают");
            return "/login/new-password";
        }
        String email = (String) request.getSession().getAttribute("userEmail");
        if (email == null || email.isEmpty()) {
            model.addAttribute("error", "Сессия истекла, пожалуйста, начните процесс снова");
            model.addAttribute("redirect", "/auth/reset");
            return "/util/redirect";
        }
        request.getSession().removeAttribute("userEmail");
        userService.changePassword(email, pas.getNewPassword());
        resetPasswordServise.deleteToken(email);
        model.addAttribute("error","Пароль успешно изменен");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
            model.addAttribute("redirect", "/profile/edit");
        } else {
            model.addAttribute("redirect", "/auth/login");
        }
        return "/util/redirect";
    }
}
