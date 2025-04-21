package kg.attractor.jobsearch.servise;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.dto.mutal.ProfileResumeDto;
import kg.attractor.jobsearch.dto.mutal.ResumeForWeb;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.*;
import kg.attractor.jobsearch.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeDao resumeDao;
    private final CategoryRepository categoryRepository;
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final EducationInfoRepository educationInfoRepository;
    private final WorkExperienceRepository workExperienceRepository;

    public List<ResumeForWeb> getResumes() {
        return resumeRepository.findByIsActiveTrue()
                .stream()
                .map(resume -> ResumeForWeb.builder()
                        .name(resume.getName())
                        .salary(resume.getSalary())
                        .updateTime(resume.getUpdateTime())
                        .categoryId(resume.getCategory().getId())
                        .author(resume.getUser().getName())
                        .build())
                .toList();
    }

    public List<ProfileResumeDto> getUserResume(String username) {
        User user = userRepository.findByEmail(username);
        return resumeRepository.getResumesByUserId(user.getId())
                .stream()
                .map(resume -> ProfileResumeDto.builder()
                        .id(resume.getId())
                        .updated(resume.getUpdateTime())
                        .name(resume.getName())
                        .build())
                .toList();
    }

    public ResumeDto getResumeById(Long resumeId,String username) {
        List<EducationInfo> educationInfos = educationInfoRepository.getEducationInfoByResumeId(resumeId);
        List<WorkExperienceInfo> works = workExperienceRepository.getWorkExperienceInfoByResumeId(resumeId);
        User user = userRepository.findByEmail(username);
        if (!resumeRepository.existsByResumeIdAndUserId(resumeId, user.getId())) {
            throw new NotFound("Resume with id " + resumeId + " not found");
        }
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));
        return ResumeDto.builder()
                .name(resume.getName())
                .categoryId(resume.getCategory().getId())
                .salary(resume.getSalary())
                .isActive(resume.getIsActive())
                .updateTime(resume.getUpdateTime())
                .createdDate(resume.getCreatedDate())
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

        public ResponseEntity<ResumeDto> updateResume(Long resumeId, ResumeDto resumeDto) {
            Resume oldResume = resumeRepository.findById(resumeId)
                    .orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));

            if (resumeDto.getName() == null || resumeDto.getName().isBlank()) {
                resumeDto.setName(oldResume.getName());} else {oldResume.setName(resumeDto.getName());}
            if (resumeDto.getCategoryId() == null || resumeDto.getCategoryId() <= 0) {
                resumeDto.setCategoryId(oldResume.getCategory().getId());}else {
                Category category = categoryRepository.findById(resumeDto.getCategoryId()).orElse(null);
                oldResume.setCategory(category);}
            if (resumeDto.getSalary() == null || resumeDto.getSalary() <= 0) {
                resumeDto.setSalary(oldResume.getSalary());}else {
                oldResume.setSalary(resumeDto.getSalary());}
            if (resumeDto.getIsActive() == null){oldResume.setIsActive(false);}else {
                if (resumeDto.getIsActive()){
                    oldResume.setIsActive(true);
                }
            }
            LocalDate oldCreatedDate = oldResume.getCreatedDate();
            resumeDto.setCreatedDate(oldCreatedDate);
            resumeDto.setUpdateTime(LocalDate.now());

            resumeRepository.save(oldResume);
            return ResponseEntity.status(HttpStatus.OK).body(resumeDto);
        }

        public void updateTime(Long resumeId) {
            Resume resume = resumeRepository.findById(resumeId).orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));
            resume.setUpdateTime(LocalDate.now());
            resumeRepository.save(resume);
        }
}