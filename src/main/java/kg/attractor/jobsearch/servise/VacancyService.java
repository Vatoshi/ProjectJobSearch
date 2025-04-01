package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.ResumeFromUserNotFound;
import kg.attractor.jobsearch.models.Resume;
import kg.attractor.jobsearch.models.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyDao vacancyDao;

    public List<UserDto> getResponedUsers(Long vacancyId) throws IllegalArgumentException {
        return vacancyDao.getRespondedUsersOnVacancy(vacancyId)
                .stream()
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

    public List<VacancyDto> getVacancyByCategory(String category) throws NotFound {
        return vacancyDao.findByCategory(category)
                .stream()
                .map(vacancy -> VacancyDto.builder()
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .expFrom(vacancy.getExpFrom())
                        .expTo(vacancy.getExpTo())
                        .categoryId(vacancy.getCategoryId())
                        .createdDate(vacancy.getCreatedDate())
                        .updateTime(vacancy.getUpdateTime())
                        .isActive(vacancy.getIsActive())
                        .salary(vacancy.getSalary())
                        .build())
                .filter(VacancyDto::getIsActive)
                .toList();
    }

    public List<VacancyDto> getAllVacancies() throws NotFound {
       return vacancyDao.getAllVacancies()
               .stream()
               .map(vacancy -> VacancyDto.builder()
                       .name(vacancy.getName())
                       .description(vacancy.getDescription())
                       .expFrom(vacancy.getExpFrom())
                       .expTo(vacancy.getExpTo())
                       .categoryId(vacancy.getCategoryId())
                       .createdDate(vacancy.getCreatedDate())
                       .updateTime(vacancy.getUpdateTime())
                       .isActive(vacancy.getIsActive())
                       .salary(vacancy.getSalary())
                       .build())
               .filter(VacancyDto::getIsActive)
               .toList();
    }

    public List<VacancyDto> getApplicantsForVacancy(Long user) throws NotFound {
        return vacancyDao.getVacanciesResponses(user)
                .stream()
                .map(vacancy -> VacancyDto.builder()
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .expFrom(vacancy.getExpFrom())
                        .expTo(vacancy.getExpTo())
                        .categoryId(vacancy.getCategoryId())
                        .createdDate(vacancy.getCreatedDate())
                        .updateTime(vacancy.getUpdateTime())
                        .isActive(vacancy.getIsActive())
                        .salary(vacancy.getSalary())
                        .build())
                .filter(VacancyDto::getIsActive)
                .toList();
    }

    public ResponseEntity<VacancyDto> createVacancy(VacancyDto vacancyDto) throws IllegalArgumentException {
        vacancyDto.setCreatedDate(LocalDateTime.now());
        vacancyDto.setUpdateTime(LocalDateTime.now());

        Vacancy vacancy = Vacancy.builder()
                .authorId(2L)
                .name(vacancyDto.getName())
                .categoryId(vacancyDto.getCategoryId())
                .salary(vacancyDto.getSalary())
                .isActive(vacancyDto.getIsActive())
                .updateTime(vacancyDto.getUpdateTime())
                .createdDate(vacancyDto.getCreatedDate())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .description(vacancyDto.getDescription())
                .build();

        vacancyDao.createVacancy(vacancyDto, vacancy);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyDto);
    }

    public HttpStatus deleteVacancy(Long vacancyId) throws NotFound {
        return vacancyDao.deleteVacancy(vacancyId);
    }

    public ResponseEntity<VacancyDto> updateResume(Long vacancyId, VacancyDto vacancyDto) throws NotFound {
        Vacancy oldVacancy = vacancyDao.getVacancy(vacancyId)
                .orElseThrow(() -> new NotFound("Vacancy not found"));

        if (vacancyDto.getName().equalsIgnoreCase("") || vacancyDto.getName().isEmpty()) {
            vacancyDto.setName(oldVacancy.getName());
        }
        if (vacancyDto.getDescription().equalsIgnoreCase("") || vacancyDto.getDescription().isEmpty()) {
            vacancyDto.setDescription(oldVacancy.getDescription());
        }
        if (vacancyDto.getCategoryId() == null) {
            vacancyDto.setCategoryId(oldVacancy.getCategoryId());
        }

        LocalDateTime oldCreatedDate = oldVacancy.getCreatedDate();
        vacancyDto.setCreatedDate(oldCreatedDate);
        vacancyDto.setUpdateTime(LocalDateTime.now());
        vacancyDao.updateVacancy(vacancyDto,vacancyId);
        return ResponseEntity.status(HttpStatus.OK).body(vacancyDto);
    }
}