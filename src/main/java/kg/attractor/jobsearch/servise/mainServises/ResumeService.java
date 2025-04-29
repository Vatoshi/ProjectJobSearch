package kg.attractor.jobsearch.servise.mainServises;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.dto.mutal.ResumeDetailsDto;
import kg.attractor.jobsearch.dto.mutal.ResumeForWeb;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.NotOwnVacancy;
import kg.attractor.jobsearch.models.*;
import kg.attractor.jobsearch.repositories.*;
import kg.attractor.jobsearch.servise.CategoryServise;
import kg.attractor.jobsearch.servise.EducationInfoServise;
import kg.attractor.jobsearch.servise.WorkExperienceServise;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResumeService {
    private final CategoryServise categoryServise;
    private final ResumeRepository resumeRepository;
    private final UserService userService;
    private final EducationInfoServise educationInfoServise;
    private final WorkExperienceServise workExperienceServise;

    public ResumeDetailsDto getResumeById(Long resumeId) {
        List<EducationInfo> educationInfos = educationInfoServise.getEducationInfoByResumeId(resumeId);
        List<WorkExperienceInfo> works = workExperienceServise.getWorkExperienceByResumeId(resumeId);
        Resume resume = resumeRepository.getResumeById(resumeId);
        return ResumeDetailsDto.builder()
                .name(resume.getName())
                .author(resume.getUser().getName())
                .categoryId(resume.getCategory().getId())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .updateTime(LocalDate.from(resume.getUpdateTime()))
                .createdDate(LocalDate.from(resume.getCreatedDate()))
                .educationInfo(educationInfos.stream()
                        .map(educationInfo -> new EducationInfoDto(educationInfo.getInstitution()
                                ,educationInfo.getProgram()
                                ,educationInfo.getStartDate()
                                ,educationInfo.getEndDate()
                                ,educationInfo.getDegree()))
                        .toList())
                .workExperienceInfo(works.stream()
                        .map(work -> new WorkExperienceInfoDto(work.getYears(),work.getCompanyName(),
                                work.getPosition(),
                                work.getResponsibilities()))
                        .toList())
                .build();
    }

    public Page<ResumeForWeb> getResumes(Pageable pageable) {
        return resumeRepository.findByIsActiveTrue(pageable)
                .map(resume -> ResumeForWeb.builder()
                        .name(resume.getName())
                        .id(resume.getId())
                        .salary(resume.getSalary())
                        .updateTime(LocalDate.from(resume.getUpdateTime()))
                        .categoryId(resume.getCategory().getId())
                        .author(resume.getUser().getName())
                        .build());
    }

    public ResumeDto getResumeByUserIdAndId(Long resumeId,String username) {
        List<EducationInfo> educationInfos = educationInfoServise.getEducationInfoByResumeId(resumeId);
        List<WorkExperienceInfo> works = workExperienceServise.getWorkExperienceByResumeId(resumeId);
        User user = userService.getUserByEmail(username);
        if (!resumeRepository.existsByResumeIdAndUserId(resumeId, user.getId())) {
            throw new NotOwnVacancy("Resume with id " + resumeId + " not found");
        }
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));
        return ResumeDto.builder()
                .name(resume.getName())
                .categoryId(resume.getCategory().getId())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .updateTime(LocalDate.from(resume.getUpdateTime()))
                .createdDate(LocalDate.from(resume.getCreatedDate()))
                .educationInfo(educationInfos.stream()
                        .map(educationInfo -> new EducationInfoDto(educationInfo.getInstitution()
                                ,educationInfo.getProgram()
                                ,educationInfo.getStartDate()
                                ,educationInfo.getEndDate()
                                ,educationInfo.getDegree()))
                        .toList())
                .workExperienceInfo(works.stream()
                        .map(work -> new WorkExperienceInfoDto(work.getYears(),work.getCompanyName(),
                                work.getPosition(),
                                work.getResponsibilities()))
                        .toList())
                .build();
    }

    @Transactional
    public void createResume(ResumeDto resumeDto, String username) throws IllegalArgumentException {
            resumeDto.setCreatedDate(LocalDate.now());
            resumeDto.setUpdateTime(LocalDate.now());
            User user = userService.getUserByEmail(username);
            Category category = categoryServise.getCategory(resumeDto.getCategoryId());
        Resume resume = Resume.builder()
                .user(user)
                .name(resumeDto.getName())
                .category(category)
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.getIsActive())
                .updateTime(resumeDto.getUpdateTime().atStartOfDay())
                .createdDate(resumeDto.getCreatedDate().atStartOfDay())
                .build();
            resumeRepository.saveAndFlush(resume);

            List<WorkExperienceInfo> works = (resumeDto.getWorkExperienceInfo().stream().map(dto -> WorkExperienceInfo.builder()
                    .years(dto.getYears())
                    .responsibilities(dto.getResponsibilities())
                    .position(dto.getPosition())
                    .companyName(dto.getCompanyName())
                    .resume(resume)
                    .build())
                    .toList());
            workExperienceServise.saveAll(works);

            List<EducationInfo> educ = (resumeDto.getEducationInfo().stream().map(dto -> EducationInfo.builder()
                    .degree(dto.getDegree())
                    .endDate(dto.getEndDate())
                    .institution(dto.getInstitution())
                    .program(dto.getProgram())
                    .startDate(dto.getStartDate())
                    .resume(resume)
                    .build()).toList());
            educationInfoServise.saveEducationInfo(educ);
        ResponseEntity.status(HttpStatus.CREATED).body(resumeDto);
    }

        public void updateResume(Long resumeId, ResumeDto resumeDto) {
            Resume oldResume = resumeRepository.findById(resumeId)
                    .orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));

            if (resumeDto.getName() == null || resumeDto.getName().isBlank()) {
                resumeDto.setName(oldResume.getName());} else {oldResume.setName(resumeDto.getName());}
            if (resumeDto.getCategoryId() == null || resumeDto.getCategoryId() <= 0) {
                resumeDto.setCategoryId(oldResume.getCategory().getId());}else {
                Category category = categoryServise.getCategory(resumeDto.getCategoryId());
                oldResume.setCategory(category);}
            if (resumeDto.getSalary() == null || resumeDto.getSalary() <= 0) {
                resumeDto.setSalary(oldResume.getSalary());}else {
                oldResume.setSalary(resumeDto.getSalary());}
            if (resumeDto.getIsActive() == null){oldResume.setIsActive(false);}else {
                if (resumeDto.getIsActive()){
                    oldResume.setIsActive(true);
                }
            }
            oldResume.setUpdateTime(LocalDateTime.now());

            resumeRepository.save(oldResume);
            ResponseEntity.status(HttpStatus.OK).body(resumeDto);
        }

        public void updateTime(Long resumeId) {
            Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));
            resume.setUpdateTime(LocalDateTime.now());
            resumeRepository.save(resume);
        }

}