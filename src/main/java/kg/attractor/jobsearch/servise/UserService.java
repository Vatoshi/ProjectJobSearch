package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dto.ImageDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserFormDto;
import kg.attractor.jobsearch.exeptions.AlreadyExists;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.UsernameNotFound;
import kg.attractor.jobsearch.models.User;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserDao userDao;
    private final FileUtil fileUtil;

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

    public String saveImage(ImageDto imageDto) {
        User user = userDao.findById(imageDto.getUserId())
                .orElseThrow(UsernameNotFound::new);
        String filename = fileUtil.saveUploadFile(imageDto.getImage(), "images/");
        userDao.save(filename,imageDto.getUserId());
        return filename;
    }

    public UserFormDto createAcc(UserFormDto u) {
        String gmail = u.getEmail();
        if (u.getEmail() == null || gmail.equals(userDao.getExistEmail(u.getEmail()))) {
            throw new AlreadyExists("User with email " + u.getEmail() + " already exists");
        }

        User newUser = User.builder()
                .name(u.getName())
                .surname(u.getSurname())
                .age(u.getAge())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .password(u.getPassword())
                .phoneNumber(u.getPhone())
                .accountType(u.getAccountType())
                .enabled(true)
                .roleId(u.getAccountType() == null ? 2L : 3L)
                .build();

        userDao.createAcc(newUser);
        return u;
    }

//    public UserFormDto editAcc(UserFormDto u) {
//
//    }
}


