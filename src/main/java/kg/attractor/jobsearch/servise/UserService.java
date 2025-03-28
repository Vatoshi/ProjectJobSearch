package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.UsernameNotFound;
import kg.attractor.jobsearch.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserDao userDao;

    public UserDto getEmail(String email) {
        User user = userDao.findByEmail(email)
                .orElseThrow(UsernameNotFound::new);
        return UserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .surname(user.getSurname())
                .phone(user.getPhoneNumber())
                .accountType(user.getAccountType())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public List<UserDto> getUsersByName(String name) {
        List<User> users = userDao.findByName(name);
        if (users.isEmpty()) {
            throw new NotFound("Users with name " + name + " not found");
        }
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .age(user.getAge())
                        .surname(user.getSurname())
                        .phone(user.getPhoneNumber())
                        .accountType(user.getAccountType())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .toList();
    }

    public List<UserDto> getUsersByPhone(String phone) {
        List<User> users = userDao.findByPhone(phone);
        if (users.isEmpty()) {
            throw new NotFound("Users with phone " + phone + " not found");
        }
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .age(user.getAge())
                        .surname(user.getSurname())
                        .phone(user.getPhoneNumber())
                        .accountType(user.getAccountType())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .toList();
    }


}


