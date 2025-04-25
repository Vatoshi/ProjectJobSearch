package kg.attractor.jobsearch.servise.mainServises;

import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.dto.UserFormDto;
import kg.attractor.jobsearch.dto.mutal.UserProfile;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.Role;
import kg.attractor.jobsearch.models.User;
import kg.attractor.jobsearch.repositories.UserRepository;
import kg.attractor.jobsearch.servise.RoleServise;
import kg.attractor.jobsearch.servise.helpers.UserResumeServise;
import kg.attractor.jobsearch.servise.helpers.UserVacancyServise;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    private final FileUtil fileUtil;
    private final RoleServise roleServise;
    private final UserResumeServise userResumeServise;
    private final UserVacancyServise userVacancyServise;

    public List<User> getUsersByRoleId(Pageable pageable, long roleId) {
        return userRepository.getUsersByRoleId(pageable, roleId);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFound("User not found"));
    }

    public Long userId(String username) {
        User user = userRepository.findByEmail(username)    ;
        return user.getId();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserProfile getUserForProfile(String email, Pageable pageable) {
        User user = userRepository.findByEmail(email);
        return UserProfile.builder()
                .userId(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .enabled(user.getEnabled())
                .role(user.getRole())
                .vacancies(user.getVacancies())
                .resumes(null)
                .vacancies(null)
                .age(user.getAge())
                .email(user.getEmail())
                .build();
    }

    public String saveImage(MultipartFile image, String username) {
        String filename = fileUtil.saveUploadFile(image, "images/");
        User user = userRepository.findByEmail(username);
        user.setAvatar(filename);
        userRepository.save(user);
        return filename;
    }

    public UserFormDto createAcc(UserFormDto u)  throws HttpMessageNotReadableException  {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Role role = roleServise.findById(u.getRoleId());
        if (u.getAvatar() == null) {
            u.setAvatar("Default.png");
        }
        User newUser = User.builder()
                .name(u.getName())
                .surname(u.getSurname())
                .age(u.getAge())
                .avatar(u.getAvatar())
                .email(u.getEmail())
                .password(encoder.encode(u.getPassword()))
                .phoneNumber(u.getPhoneNumber())
                .enabled(true)
                .role(role)
                .build();

        userRepository.save(newUser);
        return u;
    }

    public Boolean existEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserEditDto editAcc(UserEditDto u, Long userId) throws HttpMessageNotReadableException {
        User oldUser = userRepository.findById(userId).orElseThrow(() -> new NotFound("User with id " + userId + " not found"));
        if (u.getName() == null || u.getName().isEmpty()) {u.setName(oldUser.getName());}
        if (u.getSurname() == null || u.getSurname().isEmpty()) {u.setSurname(oldUser.getSurname());}
        if (u.getAge() == null || u.getAge() < 14 || u.getAge() > 120) {u.setAge(oldUser.getAge());}

        if (u.getEmail() == null || u.getEmail().isEmpty()) {u.setEmail(oldUser.getEmail());}
        u.setPassword(oldUser.getPassword());
        if (u.getAvatar() == null) {u.setAvatar(oldUser.getAvatar());}
        if (u.getPhoneNumber() == null || u.getPhoneNumber().isEmpty()) {u.setPhoneNumber(oldUser.getPhoneNumber());}

        userRepository.save(oldUser);
        return u;
    }

}


