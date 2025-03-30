package kg.attractor.jobsearch.servise;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exeptions.EntityForDeleteNotFound;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.UserStatusExeption;
import kg.attractor.jobsearch.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResumeService {
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
            if (resumeDto.getName() == null || resumeDto.getName().isBlank()) {
                String oldName = resumeDao.getName(resumeId);
                resumeDto.setName(oldName);
            }

            if (resumeDto.getCategoryId() == 0 || resumeDto.getCategoryId() < 0) {
                int oldId = resumeDao.getCategoryId(resumeId);
                resumeDto.setCategoryId(oldId);
            }

            if (resumeDto.getSalary() == 0 || resumeDto.getSalary() < 0) {
                Double oldSalary = resumeDao.getSalary(resumeId);
                resumeDto.setSalary(oldSalary);
            }

            LocalDateTime oldCreatedDate = resumeDao.getCreatedDate(resumeId);
            resumeDto.setCreatedDate(oldCreatedDate);
            resumeDto.setUpdateTime(LocalDateTime.now());
            return resumeDao.updateResume(resumeDto,resumeId);
        }
}