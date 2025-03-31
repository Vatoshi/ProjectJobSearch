package kg.attractor.jobsearch.servise;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.EducationInfo;
import kg.attractor.jobsearch.models.Resume;
import kg.attractor.jobsearch.models.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResumeService {
    private final UserService dao;
    private final ResumeDao resumeDao;

    public List<ResumeDto> getResumesById(String categoryName) throws NotFound {
        List<Resume> resumes = resumeDao.findByCategory(categoryName);
        return resumes.stream()
                .map(resume -> ResumeDto.builder()
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .updateTime(resume.getUpdateTime())
                        .createdDate(resume.getCreatedDate())
                        .build())
                .toList();
    }

    public List<ResumeDto> getResumesByAplicant(Long userId) {
        List<Resume> resumes = resumeDao.findByUser(userId);
        if (resumes.isEmpty()) {
            throw new NotFound("resume not found");
        }
        return resumes.stream()
                .map(resume -> ResumeDto.builder()
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .updateTime(resume.getUpdateTime())
                        .createdDate(resume.getCreatedDate())
                        .build())
                .toList();
    }

    public ResumeDto getResumeById(Long resumeId) {
        Resume resume = resumeDao.findResumeById(resumeId)
                .orElseThrow(() -> new NotFound("Could not find resume with id: " + resumeId));
        return ResumeDto.builder()
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .updateTime(resume.getUpdateTime())
                .createdDate(resume.getCreatedDate())
                .educationInfo(List.of(resumeDao.getEducationInfo(resumeId)))
                .workExperienceInfo(List.of(resumeDao.getWorkExperienceInfo(resumeId)))
                .build();
    }

    public ResponseEntity<ResumeDto> createResume(ResumeDto resumeDto) throws IllegalArgumentException {
            resumeDto.setCreatedDate(LocalDateTime.now());
            resumeDto.setUpdateTime(LocalDateTime.now());

        Resume resume = Resume.builder()
                .applicantId(1L)
                .name(resumeDto.getName())
                .categoryId(resumeDto.getCategoryId())
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.isActive())
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
                resumeDto.setName(oldResume.getName());
            }
            if (resumeDto.getCategoryId() == 0 || resumeDto.getCategoryId() < 0) {
                resumeDto.setCategoryId(oldResume.getCategoryId());
            }
            if (resumeDto.getSalary() == 0 || resumeDto.getSalary() < 0) {
                resumeDto.setSalary(oldResume.getSalary());
            }

            if (resumeDto.getEducationInfo() == null) {
                EducationInfoDto oldEduc = resumeDao.getEducationInfo(oldResume.getId());
                resumeDto.setEducationInfo(List.of(oldEduc));
                resumeDao.updateEducationInfo(resumeId, oldEduc);
            } else {
                EducationInfoDto educDto = resumeDto.getEducationInfo().getFirst();
                resumeDao.updateEducationInfo(resumeId, educDto);
            }
            if (resumeDto.getWorkExperienceInfo() == null) {
                WorkExperienceInfoDto oldWorkExperience = resumeDao.getWorkExperienceInfo(oldResume.getId());
                resumeDto.setWorkExperienceInfo(List.of(oldWorkExperience));
                resumeDao.updateWorkExperienceInfo(resumeId, oldWorkExperience);
            } else {
                WorkExperienceInfoDto workDto = resumeDto.getWorkExperienceInfo().getFirst();
                resumeDao.updateWorkExperienceInfo(resumeId, workDto);
            }

            LocalDateTime oldCreatedDate = oldResume.getCreatedDate();
            resumeDto.setCreatedDate(oldCreatedDate);
            resumeDto.setUpdateTime(LocalDateTime.now());
            resumeDao.updateResume(resumeDto,resumeId);
            return ResponseEntity.status(HttpStatus.OK).body(resumeDto);
        }
}