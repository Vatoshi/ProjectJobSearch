package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.dto.VacancyEditDto;
import kg.attractor.jobsearch.dto.mutal.ProfileVacancyDto;
import kg.attractor.jobsearch.dto.mutal.VacancyForWebDto;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyDao vacancyDao;

    public List<ProfileVacancyDto> getVacancyByUser(String username) {
        Long userId = vacancyDao.userId(username);
        return vacancyDao.getVacancyByUser(userId);
    }

    public VacancyEditDto getVacancyById(Long vacancyId,String username) {
        Long userId = vacancyDao.userId(username);
        vacancyDao.findByUser(userId,vacancyId);
        Vacancy vacancy = vacancyDao.findVacancyById(vacancyId)
                .orElseThrow(() -> new NotFound("Could not find vacancy with id: " + vacancyId));
        return VacancyEditDto.builder()
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .isActive(vacancy.getIsActive())
                .updateTime(vacancy.getUpdateTime())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .createdDate(vacancy.getCreatedDate())
                .build();
    }

    public List<UserDto> getResponedUsers(Long vacancyId) throws IllegalArgumentException {
        return vacancyDao.getRespondedUsersOnVacancy(vacancyId)
                .stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .age(user.getAge())
                        .surname(user.getSurname())
                        .phoneNumber(user.getPhoneNumber())
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

    public List<VacancyForWebDto> getAllVacancies() throws NotFound {
        return vacancyDao.getAllVacancies()
                .stream()
                .map(vacancy -> VacancyForWebDto.builder()
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .ExpTo(vacancy.getExpTo())
                        .ExpFrom(vacancy.getExpFrom())
                        .salary(vacancy.getSalary())
                        .isActive(vacancy.getIsActive())
                        .updateTime(LocalDate.from(vacancy.getUpdateTime()))
                        .author(vacancyDao.getAuthorName(vacancy.getAuthorId()))
                        .category(vacancyDao.getCategoryName(vacancy.getCategoryId()))
                        .build())
                .filter(VacancyForWebDto::getIsActive)
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

    public ResponseEntity<VacancyDto> createVacancy(VacancyDto vacancyDto, String username) throws IllegalArgumentException {
        vacancyDto.setCreatedDate(LocalDateTime.now());
        vacancyDto.setUpdateTime(LocalDateTime.now());
        Long userId = vacancyDao.getUserId(username);
        Vacancy vacancy = Vacancy.builder()
                .authorId(userId)
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

    public ResponseEntity<VacancyEditDto> updateVacancy(Long vacancyId, VacancyEditDto vacancyDto) throws NotFound {
        Vacancy oldVacancy = vacancyDao.getVacancy(vacancyId)
                .orElseThrow(() -> new NotFound("Vacancy not found"));
        if (vacancyDto.getName() == null) {
            vacancyDto.setName(oldVacancy.getName());}
        if (vacancyDto.getDescription() == null) {
            vacancyDto.setDescription(oldVacancy.getDescription());}
        if (vacancyDto.getCategoryId() == null) {
            vacancyDto.setCategoryId(oldVacancy.getCategoryId());}
        if (vacancyDto.getSalary() == null || vacancyDto.getSalary() < 0) {
            vacancyDto.setSalary(oldVacancy.getSalary());}
        if (vacancyDto.getExpFrom() == null || vacancyDto.getExpFrom() < 0) {
            vacancyDto.setExpFrom(oldVacancy.getExpFrom());}
        if (vacancyDto.getExpTo() == null || vacancyDto.getExpTo() < 0) {
            vacancyDto.setExpTo(oldVacancy.getExpTo());}
        if (vacancyDto.getIsActive() == null || vacancyDto.getIsActive()) {vacancyDto.setIsActive(oldVacancy.getIsActive());}
        vacancyDto.setCreatedDate(oldVacancy.getCreatedDate());
        vacancyDto.setUpdateTime(LocalDateTime.now());
        vacancyDao.updateVacancy(vacancyDto, vacancyId);

        return ResponseEntity.status(HttpStatus.OK).body(vacancyDto);
    }

    public void updateTime(Long resumeId) {
        vacancyDao.updatetime(resumeId);
    }
}
