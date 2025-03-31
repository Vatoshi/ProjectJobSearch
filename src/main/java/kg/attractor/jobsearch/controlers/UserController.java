    package kg.attractor.jobsearch.controlers;

    import jakarta.validation.Valid;
    import kg.attractor.jobsearch.dto.ImageDto;
    import kg.attractor.jobsearch.servise.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("user")
    public class UserController {
        private final UserService userService;

        @PostMapping("create")
        public ResponseEntity createUser() {
            System.out.println("something");
            return ResponseEntity.created(null).build();
        }

        @PostMapping("add-avatar")
        public String uploadImage(@Valid ImageDto ImageDto) {
            return userService.saveImage(ImageDto);
        }
    }


