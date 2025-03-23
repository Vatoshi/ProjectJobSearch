package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserDao userDao;

    public List<UserDto> getUsers() {
        List<User> list = userDao.getUsers();
        return list.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .password(e.getPassword())
                        .build())
                .toList();

    }
}


