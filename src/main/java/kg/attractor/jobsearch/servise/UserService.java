package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.exeptions.UsernameNotFound;
import kg.attractor.jobsearch.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserDao userDao;

    public UserDto getEmail(String email) {
        User user = userDao.findByEmail(email)
                .orElseThrow(UsernameNotFound::new);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public List<UserDto> getUsersByName(String name) {
        List<User> users = userDao.findByName(name);
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .toList();
    }

    public List<UserDto> getUsersByPhone(String phone) {
        List<User> users = userDao.findByPhone(phone);
        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .toList();
    }


}


