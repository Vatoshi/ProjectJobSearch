package kg.attractor.jobsearch.controlers;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.servise.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

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
