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
    private final JdbcTemplate jdbcTemplate;

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

        Resume resume = new Resume();
        resume.setName(resumeDto.getName());
        resume.setCategoryId(resumeDto.getCategoryId());
        resume.setSalary(resumeDto.getSalary());
        resume.setActive(resumeDto.isActive());
        resume.setUpdateTime(resumeDto.getUpdateTime());
        resume.setCreatedDate(LocalDateTime.now());
        resume.setApplicantId(1L);
        // у меня builder ломает предыдущие методы

        String sqltype = "select account_type from users where id = ?";
        String typename = jdbcTemplate.queryForObject(sqltype, String.class, resume.getApplicantId());
        if (typename == null || !typename.equalsIgnoreCase("applicant")) {
            throw new UserStatusExeption("wrong user status");
        }

            try {
                String sql = "insert into resumes(applicant_id, name, category_id, salary, is_active, created_date) values(?, ?, ?, ?, ?, ?)";
                jdbcTemplate.update(sql,
                        resume.getApplicantId(),
                        resume.getName(),
                        resume.getCategoryId(),
                        resume.getSalary(),
                        resume.isActive(),
                        resume.getCreatedDate()
                );
                return ResponseEntity.status(HttpStatus.CREATED).body(resumeDto);
            } catch (DataAccessException e) {
                throw new RuntimeException("Ошибка при добавлении резюме: " + e.getMessage(), e);
            }
        }

        public HttpStatus deleteResume(Long resumeId) {
            String sql = "delete from resumes where id = ?";
            int resumefound = jdbcTemplate.update(sql, resumeId);

            if (resumefound == 0) {
                throw new EntityForDeleteNotFound("resume not found");
            }
            return HttpStatus.ACCEPTED;
        }

        public ResponseEntity<ResumeDto> updateResume(Long resumeId, ResumeDto resumeDto) {
            if (resumeDto.getName() == null || resumeDto.getName().isBlank()) {
                String oldName = jdbcTemplate.queryForObject("select name from resumes where id = ?", String.class, resumeId);
                resumeDto.setName(oldName);
            }

            if (resumeDto.getCategoryId() == 0 || resumeDto.getCategoryId() < 0) {
                int oldId = jdbcTemplate.queryForObject("select category_id from resumes where id = ?", Integer.class, resumeId);
                resumeDto.setCategoryId(oldId);
            }

            if (resumeDto.getSalary() == 0 || resumeDto.getSalary() < 0) {
                Double oldSalary = jdbcTemplate.queryForObject("select salary from resumes where id = ?", Double.class, resumeId);
                resumeDto.setSalary(oldSalary);
            }

            LocalDateTime oldCreatedDate = jdbcTemplate.queryForObject("select created_date from resumes where id = ?", LocalDateTime.class, resumeId);
            resumeDto.setCreatedDate(oldCreatedDate);
            resumeDto.setUpdateTime(LocalDateTime.now());

            String sql = "update resumes set name = ?, category_id = ?, salary = ?, is_active = ?, update_time = ? where id = ?";
            jdbcTemplate.update(sql,resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.isActive(), resumeDto.getUpdateTime(), resumeId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resumeDto);
        }
}