package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.ResumeFromUserNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .expFrom(vacancy.getExpFrom())
                        .expTo(vacancy.getExpTo())
                        .authorId(vacancy.getAuthorId())
                        .categoryId(vacancy.getCategoryId())
                        .createdDate(vacancy.getCreatedDate())
                        .updatedTime(vacancy.getUpdatedTime())
                        .isActive(vacancy.isActive())
                        .salary(vacancy.getSalary())
                        .build())
                .toList();
    }

    public List<VacancyDto> getAllVacancies() throws NotFound {
       return vacancyDao.getAllVacancies()
               .stream()
               .map(vacancy -> VacancyDto.builder()
                       .id(vacancy.getId())
                       .name(vacancy.getName())
                       .description(vacancy.getDescription())
                       .expFrom(vacancy.getExpFrom())
                       .expTo(vacancy.getExpTo())
                       .authorId(vacancy.getAuthorId())
                       .categoryId(vacancy.getCategoryId())
                       .createdDate(vacancy.getCreatedDate())
                       .updatedTime(vacancy.getUpdatedTime())
                       .isActive(vacancy.isActive())
                       .salary(vacancy.getSalary())
                       .build())
               .toList();
    }

    public List<VacancyDto> getApplicantsForVacancy(Long user) throws NotFound {
        return vacancyDao.getVacanciesResponses(user)
                .stream()
                .map(vacancy -> VacancyDto.builder()
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .expFrom(vacancy.getExpFrom())
                        .expTo(vacancy.getExpTo())
                        .authorId(vacancy.getAuthorId())
                        .categoryId(vacancy.getCategoryId())
                        .createdDate(vacancy.getCreatedDate())
                        .updatedTime(vacancy.getUpdatedTime())
                        .isActive(vacancy.isActive())
                        .salary(vacancy.getSalary())
                        .build())
                .toList();
    }
}