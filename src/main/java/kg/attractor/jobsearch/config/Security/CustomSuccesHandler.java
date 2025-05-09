package kg.attractor.jobsearch.config.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kg.attractor.jobsearch.models.User;
import kg.attractor.jobsearch.servise.mainServises.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.io.IOException;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class CustomSuccesHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final LocaleResolver localeResolver;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userService.getUserByEmail(authentication.getName());
        if (user != null && user.getLanguage() != null) {
            Locale userLocale = Locale.forLanguageTag(user.getLanguage());
            localeResolver.setLocale(request, response, userLocale);
        }
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("APPLICANT"))) {
            response.sendRedirect("/");
        }else {
            response.sendRedirect("/resumes");
        }
    }
}
