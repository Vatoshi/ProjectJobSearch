    package kg.attractor.jobsearch.controlers.api;

    import kg.attractor.jobsearch.servise.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.core.io.FileSystemResource;
    import org.springframework.core.io.Resource;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;
    import java.io.File;

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

    }


