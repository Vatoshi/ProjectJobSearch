package kg.attractor.jobsearch.servise.mainServises;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.dto.VacancyEditDto;
import kg.attractor.jobsearch.dto.mutal.CompanyDto;
import kg.attractor.jobsearch.dto.mutal.VacancyForWebDto;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.NotOwnVacancy;
import kg.attractor.jobsearch.models.Category;
import kg.attractor.jobsearch.models.User;
import kg.attractor.jobsearch.models.Vacancy;
import kg.attractor.jobsearch.repositories.VacancyRepository;
import kg.attractor.jobsearch.servise.CategoryServise;
import kg.attractor.jobsearch.servise.ResponseAplicantsServise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final UserService userService;
    private final CategoryServise categoryServise;
    private final VacancyRepository vacancyRepository;
    private final ResponseAplicantsServise responseAplicantsServise;

    public VacancyForWebDto getVacancyByIdForProfile(Long vacancyId, String username) {
        User user = userService.getUserByEmail(username);
        if (!vacancyRepository.existsByUserIdAndId(user.getId(), vacancyId)) {
            throw new NotOwnVacancy("Not own Vacancy");
        }
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new NotFound("Could not find vacancy with id: " + vacancyId));
        return VacancyForWebDto.builder()
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .isActive(vacancy.getIsActive())
                .updateTime(LocalDate.from(vacancy.getUpdateTime()))
                .responses(responseAplicantsServise.getCountResponseToVacancy(vacancyId))
                .author(vacancy.getUser().getName())
                .id(vacancy.getId())
                .category(vacancy.getCategory().getName())
                .ExpTo(vacancy.getExpTo())
                .ExpFrom(vacancy.getExpFrom())
                .build();
    }

    public VacancyEditDto getVacancyById(Long vacancyId,String username) {
        User user = userService.getUserByEmail(username);
        if (!vacancyRepository.existsByUserIdAndId(user.getId(), vacancyId)) {
            throw new NotOwnVacancy("Not own Vacancy");
        }
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new NotFound("Could not find vacancy with id: " + vacancyId));
        return VacancyEditDto.builder()
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .categoryId(vacancy.getCategory().getId())
                .isActive(vacancy.getIsActive())
                .updateTime(vacancy.getUpdateTime())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .createdDate(vacancy.getCreatedDate())
                .build();
    }

    public VacancyForWebDto getVacancyById(Long vacancyId) {
        Vacancy vacancy = vacancyRepository.getVacancyById(vacancyId).orElseThrow(() -> new NotFound("Could not find vacancy with id: " + vacancyId));
        return VacancyForWebDto.builder()
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .isActive(vacancy.getIsActive())
                .updateTime(LocalDate.from(vacancy.getUpdateTime()))
                .responses(responseAplicantsServise.getCountResponseToVacancy(vacancyId))
                .author(vacancy.getUser().getName())
                .id(vacancy.getId())
                .category(vacancy.getCategory().getName())
                .ExpTo(vacancy.getExpTo())
                .ExpFrom(vacancy.getExpFrom())
                .build();
    }

    public Page<VacancyForWebDto> getAllVacancies(Pageable pageable) throws NotFound {
        Page<VacancyForWebDto> vacancies = vacancyRepository.findActiveVacancies(pageable)
                .map(vacancy -> VacancyForWebDto.builder()
                        .id(vacancy.getId())
                        .name(vacancy.getName())
                        .description(vacancy.getDescription())
                        .ExpTo(vacancy.getExpTo())
                        .ExpFrom(vacancy.getExpFrom())
                        .salary(vacancy.getSalary())
                        .updateTime(LocalDate.from(vacancy.getUpdateTime()))
                        .author(vacancy.getUser().getName())
                        .category(vacancy.getCategory().getName())
                        .responses(responseAplicantsServise.getCountResponseToVacancy(vacancy.getId()))
                        .build());

        List<VacancyForWebDto> sortByResponse = vacancies.getContent().stream()
                .sorted((v1, v2) -> Integer.compare(v2.getResponses(), v1.getResponses()))
                .toList();

        return new PageImpl<>(sortByResponse, pageable, vacancies.getTotalElements());
    }




    public void createVacancy(VacancyDto vacancyDto, String username) throws IllegalArgumentException {
        vacancyDto.setCreatedDate(LocalDateTime.now());
        vacancyDto.setUpdateTime(LocalDateTime.now());
        User user = userService.getUserByEmail(username);
        Category category = categoryServise.getCategory(vacancyDto.getCategoryId());
        Vacancy vacancy = Vacancy.builder()
                .user(user)
                .name(vacancyDto.getName())
                .category(category)
                .salary(vacancyDto.getSalary())
                .isActive(vacancyDto.getIsActive())
                .updateTime(vacancyDto.getUpdateTime())
                .createdDate(vacancyDto.getCreatedDate())
                .expFrom(vacancyDto.getExpFrom())
                .expTo(vacancyDto.getExpTo())
                .description(vacancyDto.getDescription())
                .build();

        vacancyRepository.save(vacancy);
        ResponseEntity.status(HttpStatus.CREATED).body(vacancyDto);
    }

    public void updateVacancy(Long vacancyId, VacancyEditDto vacancyDto) throws NotFound {
        Vacancy oldVacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new NotFound("Vacancy not found"));
        if (vacancyDto.getName() == null || vacancyDto.getName().isBlank()) {
            vacancyDto.setName(oldVacancy.getName());} else {oldVacancy.setName(vacancyDto.getName());}
        if (vacancyDto.getDescription() == null || vacancyDto.getDescription().isBlank()) {
            vacancyDto.setDescription(oldVacancy.getDescription());}else {oldVacancy.setDescription(vacancyDto.getDescription());}
        if (vacancyDto.getCategoryId() == null) {
            vacancyDto.setCategoryId(oldVacancy.getCategory().getId());}else { oldVacancy.setCategory(categoryServise.getCategory(vacancyDto.getCategoryId()));
        if (vacancyDto.getSalary() == null || vacancyDto.getSalary() < 0) {
            vacancyDto.setSalary(oldVacancy.getSalary());} else {oldVacancy.setSalary(vacancyDto.getSalary());}
        if (vacancyDto.getExpFrom() == null || vacancyDto.getExpFrom() <= 0) {
            vacancyDto.setExpFrom(oldVacancy.getExpFrom());} else {oldVacancy.setExpFrom(vacancyDto.getExpFrom());}
        if (vacancyDto.getExpTo() == null || vacancyDto.getExpTo() <= 0) {
            vacancyDto.setExpTo(oldVacancy.getExpTo());} else {oldVacancy.setExpTo(vacancyDto.getExpTo());}}
        if (vacancyDto.getIsActive() == null){oldVacancy.setIsActive(false);}else {
            if (vacancyDto.getIsActive()){
                oldVacancy.setIsActive(true);
            }
        }
        oldVacancy.setUpdateTime(LocalDateTime.now());
        vacancyRepository.save(oldVacancy);

        ResponseEntity.status(HttpStatus.OK).body(vacancyDto);
    }

    public void updateTime(Long resumeId) {
        Vacancy vacancy = vacancyRepository.findById(resumeId).orElseThrow(() -> new NotFound("Vacancy not found"));
        vacancy.setUpdateTime(LocalDateTime.now());
        vacancyRepository.save(vacancy);
    }

    public Page<CompanyDto> getCompanies(Pageable pageable) {
        Page<User> users = userService.getUsersByRoleId(pageable,2L);
        return  users
                .map(user -> CompanyDto.builder()
                        .phoneNumber(user.getPhoneNumber())
                        .name(user.getName())
                        .email(user.getEmail())
                        .id(user.getId())
                        .avatar(user.getAvatar())
                        .vacancies(user.getVacancies())
                        .build());
    }

    public CompanyDto getCompany(Long id) {
        User user = userService.findById(id);
        return CompanyDto.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .vacancies(user.getVacancies())
                .build();
    }
}
