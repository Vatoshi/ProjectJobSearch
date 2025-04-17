    package kg.attractor.jobsearch.controlers.api;

    import jakarta.validation.Valid;
    import kg.attractor.jobsearch.dto.ImageDto;
    import kg.attractor.jobsearch.dto.UserDto;
    import kg.attractor.jobsearch.dto.UserEditDto;
    import kg.attractor.jobsearch.dto.UserFormDto;
    import kg.attractor.jobsearch.servise.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.core.io.FileSystemResource;
    import org.springframework.core.io.Resource;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.File;
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

//        @GetMapping("/email/{email}")
//        public UserDto findByEmail(@PathVariable String email) {
//            return userService.getEmail(email);
//        }

        @GetMapping("/name/{name}")
        public List<UserDto> findByName(@PathVariable String name) {
            return userService.getUsersByName(name);
        }

        @GetMapping("/phone-number/{phone}")
        public List<UserDto> findByPhone(@PathVariable String phone) {
            return userService.getUsersByPhone(phone);
        }

    }


