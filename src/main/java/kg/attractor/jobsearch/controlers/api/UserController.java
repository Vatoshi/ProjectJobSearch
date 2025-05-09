    package kg.attractor.jobsearch.controlers.api;

    import jakarta.servlet.http.Cookie;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import kg.attractor.jobsearch.models.User;
    import kg.attractor.jobsearch.servise.mainServises.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.core.io.FileSystemResource;
    import org.springframework.core.io.Resource;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.web.servlet.LocaleResolver;
    import org.springframework.web.servlet.support.RequestContextUtils;

    import java.io.File;
    import java.io.IOException;
    import java.util.Locale;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("user")
    public class UserController {
        private final UserService userService;

        @GetMapping("/avatars/{filename}")
        public ResponseEntity<Resource> getAvatar(@PathVariable String filename) {
            File file = new File("data/images/" + filename);
            if (file.exists()) {
                Resource resource = new FileSystemResource(file);
                return ResponseEntity.ok().body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @PostMapping("add-avatar")
        public void uploadImage(MultipartFile image, Authentication auth) {
            userService.saveImage(image,auth.getName());
        }

        @PostMapping("/changeLang")
        public void changeLanguage(Authentication auth,
                                   @RequestParam String lang,
                                   HttpServletResponse response,
                                   HttpServletRequest request) throws IOException {
            if (auth != null && (lang.equals("en") || lang.equals("ru"))) {
                userService.saveLanguage(lang, auth.getName());
                UsernamePasswordAuthenticationToken newAuth =
                        new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), auth.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(newAuth);
            }

            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if (localeResolver != null) {
                localeResolver.setLocale(request, response, Locale.forLanguageTag(lang));
            }

            String referer = request.getHeader("referer");
            response.sendRedirect(referer != null ? referer : "/?lang=" + lang);
        }

    }


