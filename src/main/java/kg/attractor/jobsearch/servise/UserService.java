package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dto.ImageDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.dto.UserFormDto;
import kg.attractor.jobsearch.enums.AccountType;
import kg.attractor.jobsearch.exeptions.AlreadyExists;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.UsernameNotFound;
import kg.attractor.jobsearch.models.User;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
                .phoneNumber(user.getPhoneNumber())
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
                        .phoneNumber(user.getPhoneNumber())
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
                        .phoneNumber(user.getPhoneNumber())
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

    public UserFormDto createAcc(UserFormDto u)  throws HttpMessageNotReadableException  {
        String gmail = u.getEmail();
        if (u.getEmail() == null || gmail.equals(userDao.getExistEmail(u.getEmail()))) {
            throw new AlreadyExists("User with email " + u.getEmail() + " already exists");
        }
        if (u.getAvatar() == null) {
            u.setAvatar("Default.png");
        }
        User newUser = User.builder()
                .name(u.getName())
                .surname(u.getSurname())
                .age(u.getAge())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .password(u.getPassword())
                .phoneNumber(u.getPhoneNumber())
                .enabled(true)
                .roleId(u.getRoleId())
                .build();

        userDao.createAcc(newUser);
        return u;
    }

    public UserEditDto editAcc(UserEditDto u, Long userId) throws HttpMessageNotReadableException {
        User oldUser = userDao.findById(userId).orElseThrow(UsernameNotFound::new);
        if (u.getName() == null) {u.setName(oldUser.getName());}
        if (u.getSurname() == null) {u.setSurname(oldUser.getSurname());}
        if (u.getAge() == null || u.getAge() < 14 || u.getAge() > 120) {u.setAge(oldUser.getAge());}

        if (userDao.getExistEmail(u.getEmail()) != null) { throw new AlreadyExists("User with email " + u.getEmail() + " already exists");}
        if (u.getEmail() == null) {u.setEmail(oldUser.getEmail());}
        u.setPassword(oldUser.getPassword());
        if (u.getAvatar() == null) {u.setAvatar(oldUser.getAvatar());}
        if (u.getPhoneNumber() == null) {u.setPhoneNumber(oldUser.getPhoneNumber());}

        userDao.updateUser(u,userId);
        return u;
    }

    public HttpStatus deleteAcc(Long userId) {
        return userDao.deleteAcc(userId);
    }
}


