    package kg.attractor.jobsearch.controlers;

    import jakarta.validation.Valid;
    import kg.attractor.jobsearch.dto.ImageDto;
    import kg.attractor.jobsearch.dto.UserDto;
    import kg.attractor.jobsearch.dto.UserEditDto;
    import kg.attractor.jobsearch.dto.UserFormDto;
    import kg.attractor.jobsearch.servise.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.HttpStatusCode;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("user")
    public class UserController {
        private final UserService userService;

        @PostMapping("create")
        public ResponseEntity<UserFormDto> createUser(@Valid @RequestBody UserFormDto userFormDto) {
            userService.createAcc(userFormDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userFormDto);
        }

        @PostMapping("edit/{userId}")
        public ResponseEntity<UserEditDto> editUser(@Valid @RequestBody UserEditDto userEditDto, @PathVariable Long userId) {
            userService.editAcc(userEditDto, userId);
            return ResponseEntity.status(HttpStatus.OK).body(userEditDto);
        }

        @DeleteMapping("delete/{userId}")
        public HttpStatus deleteUser(@PathVariable Long userId) {
            return userService.deleteAcc(userId);
        }

        @PostMapping("add-avatar")
        public String uploadImage(@Valid ImageDto ImageDto) {
            return userService.saveImage(ImageDto);
        }

        @GetMapping("/email/{email}")
        public UserDto findByEmail(@PathVariable String email) {
            return userService.getEmail(email);
        }

        @GetMapping("/name/{name}")
        public List<UserDto> findByName(@PathVariable String name) {
            return userService.getUsersByName(name);
        }

        @GetMapping("/phone-number/{phone}")
        public List<UserDto> findByPhone(@PathVariable String phone) {
            return userService.getUsersByPhone(phone);
        }

    }


