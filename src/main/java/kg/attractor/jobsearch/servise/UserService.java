package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.dto.UserFormDto;
import kg.attractor.jobsearch.dto.mutal.UserProfile;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.Role;
import kg.attractor.jobsearch.models.User;
import kg.attractor.jobsearch.repositories.ResumeRepository;
import kg.attractor.jobsearch.repositories.RoleRepository;
import kg.attractor.jobsearch.repositories.UserRepository;
import kg.attractor.jobsearch.repositories.VacancyRepository;
import kg.attractor.jobsearch.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserService {
    public final UserRepository userRepository;
    private final FileUtil fileUtil;
    private final RoleRepository roleRepository;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;

    public Long userId(String username) {
        User user = userRepository.findByEmail(username);
        return user.getId();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserProfile getUserForProfile(String email, Pageable pageable) {
        User user = userRepository.findByEmail(email);
        return UserProfile.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .enabled(user.getEnabled())
                .role(user.getRole())
                .vacancies(user.getVacancies())
                .resumes(resumeRepository.getResumesByUserId(user.getId(), pageable))
                .vacancies(vacancyRepository.getVacanciesByUserId(user.getId(), pageable))
                .age(user.getAge())
                .email(user.getEmail())
                .build();
    }

    public int totalResumeByUser(int size, String username){
        User user = userRepository.findByEmail(username);
        int count = resumeRepository.getResumesCountNonActive(user.getId());
        int page = count / size;
        if (count%size == 0 || count < size){
            return page;
        } else {
            return page + 1;
        }
    }

    public int totalVacancyByUser(int size, String username){
        User user = userRepository.findByEmail(username);
        int count = vacancyRepository.getVacancyCountNotActive(user.getId());
        int page = count / size;
        if (count%size == 0 || count < size){
            return page;
        } else {
            return page + 1;
        }
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
        Role role = roleRepository.findById(u.getRoleId()).orElseThrow(() -> new NotFound("Role with id " + u.getRoleId() + " not found"));
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

    public void deleteAcc(Long userId) {
        userRepository.deleteById(userId);
    }
}


