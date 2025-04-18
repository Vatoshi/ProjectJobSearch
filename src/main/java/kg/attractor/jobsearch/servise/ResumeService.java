package kg.attractor.jobsearch.servise;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.dto.mutal.ProfileResumeDto;
import kg.attractor.jobsearch.dto.mutal.ResumeForWeb;
import kg.attractor.jobsearch.dto.mutal.VacancyForWebDto;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.*;
import kg.attractor.jobsearch.repositories.CategoryRepository;
import kg.attractor.jobsearch.repositories.ResumeRepository;
import kg.attractor.jobsearch.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResumeService {
    private final UserService dao;
    private final ResumeDao resumeDao;
    private final CategoryRepository categoryRepository;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public List<ResumeForWeb> getResumes() {
        return resumeRepository.findAll()
                .stream()
                .filter(Resume::getIsActive)
                .map(resume -> ResumeForWeb.builder()
                        .name(resume.getName())
                        .salary(resume.getSalary())
                        .updateTime(resume.getUpdateTime())
                        .categoryId(resume.getCategory().getId())
                        .author(resumeDao.getUserName(resume.getUser().getId()))
                        .build())
                .toList();
    }


    public List<ResumeDto> getResumesById(String categoryName) throws NotFound {
        List<Resume> resumes = resumeRepository.findActiveByCategoryName(categoryName).stream().filter(Resume::getIsActive).toList();
        return resumes.stream()
                .map(resume -> ResumeDto.builder()
                        .name(resume.getName())
                        .categoryId(resume.getCategory().getId())
                        .salary(resume.getSalary())
                        .isActive(resume.getIsActive())
                        .updateTime(resume.getUpdateTime())
                        .createdDate(resume.getCreatedDate())
                        .build())
                .toList();
    }

    public List<ProfileResumeDto> getUserResume(String username) {
        Long userId = resumeDao.userId(username);
        return resumeDao.getResumeByUser(userId);
    }

//    public List<ResumeDto> getResumesByAplicant(Long userId) {
//        List<Resume> resumes.ftlh = resumeDao.findByUser(userId).stream().filter(Resume::getIsActive).toList();
//        if (resumes.ftlh.isEmpty()) {
//            throw new NotFound("resume not found");
//        }
//
//        return resumes.ftlh.stream()
//                .map(resume -> ResumeDto.builder()
//                        .name(resume.getName())
//                        .categoryId(resume.getCategoryId())
//                        .salary(resume.getSalary())
//                        .isActive(resume.getIsActive())
//                        .updateTime(resume.getUpdateTime())
//                        .createdDate(resume.getCreatedDate())
//                        .build())
//                .toList();
//    }

    public ResumeDto getResumeById(Long resumeId,String username) {
        Long userId = resumeDao.userId(username);
        resumeDao.findByUser(userId,resumeId);
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));
        return ResumeDto.builder()
                .name(resume.getName())
                .categoryId(resume.getCategory().getId())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .updateTime(resume.getUpdateTime())
                .createdDate(resume.getCreatedDate())
                .educationInfo(List.of(resumeDao.getEducationInfo(resumeId)))
                .workExperienceInfo(List.of(resumeDao.getWorkExperienceInfo(resumeId)))
                .build();
    }

    public ResponseEntity<ResumeDto> createResume(ResumeDto resumeDto, String username) throws IllegalArgumentException {
            resumeDto.setCreatedDate(LocalDate.now());
            resumeDto.setUpdateTime(LocalDate.now());
            User user = userRepository.findByEmail(username);
            Category category = categoryRepository.findById(resumeDto.getCategoryId()).orElseThrow(() -> new NotFound("Could not find category with id: " + resumeDto.getCategoryId()));
        Resume resume = Resume.builder()
                .user(user)
                .name(resumeDto.getName())
                .category(category)
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .updateTime(resumeDto.getUpdateTime())
                .createdDate(resumeDto.getCreatedDate())
                .build();

            resumeDao.createResume(resumeDto, resume);
            return ResponseEntity.status(HttpStatus.CREATED).body(resumeDto);
    }

        public HttpStatus deleteResume(Long resumeId) {
            return resumeDao.deleteResume(resumeId);
        }

        public ResponseEntity<ResumeDto> updateResume(Long resumeId, ResumeDto resumeDto) {
            Resume oldResume = resumeDao.findResumeById(resumeId)
                    .orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));

            if (resumeDto.getName() == null || resumeDto.getName().isBlank()) {
                resumeDto.setName(oldResume.getName());}
            if (resumeDto.getCategoryId() == null || resumeDto.getCategoryId() <= 0) {
                resumeDto.setCategoryId(oldResume.getCategory().getId());}
            if (resumeDto.getSalary() == null || resumeDto.getSalary() <= 0) {
                resumeDto.setSalary(oldResume.getSalary());}
            if (resumeDto.getIsActive() == null){resumeDto.setIsActive(false);}

            if (resumeDto.getEducationInfo() == null || resumeDto.getEducationInfo().isEmpty()) {
                EducationInfoDto oldEduc = resumeDao.getEducationInfo(oldResume.getId());
                resumeDto.setEducationInfo(List.of(oldEduc));
                resumeDao.updateEducationInfo(resumeId, oldEduc);
            } else {
                EducationInfoDto educDto = resumeDto.getEducationInfo().getFirst();
                resumeDao.updateEducationInfo(resumeId, educDto);}
            if (resumeDto.getWorkExperienceInfo() == null || resumeDto.getWorkExperienceInfo().isEmpty()) {
                WorkExperienceInfoDto oldWorkExperience = resumeDao.getWorkExperienceInfo(oldResume.getId());
                resumeDto.setWorkExperienceInfo(List.of(oldWorkExperience));
                resumeDao.updateWorkExperienceInfo(resumeId, oldWorkExperience);
            } else {
                WorkExperienceInfoDto workDto = resumeDto.getWorkExperienceInfo().getFirst();
                resumeDao.updateWorkExperienceInfo(resumeId, workDto);}

            LocalDate oldCreatedDate = oldResume.getCreatedDate();
            resumeDto.setCreatedDate(oldCreatedDate);
            resumeDto.setUpdateTime(LocalDate.now());
            resumeDao.updateResume(resumeDto,resumeId);
            return ResponseEntity.status(HttpStatus.OK).body(resumeDto);
        }

        public void updateTime(Long resumeId) {
            resumeDao.updatetime(resumeId);
        }
}