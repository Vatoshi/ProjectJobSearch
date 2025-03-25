package kg.attractor.jobsearch.servise;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dto.ResumeDto;
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

    public List<ResumeDto> getResumesById(String categoryName) {
        List<Resume> resumes = resumeDao.findByCategory(categoryName);
        return resumes.stream()
                .map(resume -> ResumeDto.builder()
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .build())
                .toList();
    }

    public List<ResumeDto> getResumesByAplicant(Long userId) {
        List<Resume> resumes = resumeDao.findByUser(userId);
        return resumes.stream()
                .map(resume -> ResumeDto.builder()
                        .name(resume.getName())
                        .categoryId(resume.getCategoryId())
                        .salary(resume.getSalary())
                        .isActive(resume.isActive())
                        .build())
                .toList();
    }

    public ResumeDto getResumeById(Long resumeId) {
        Resume resume = resumeDao.findResumeById(resumeId)
                .orElseThrow(() -> new RuntimeException("Could not find resume with id: " + resumeId));
        return ResumeDto.builder()
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .isActive(resume.isActive())
                .build();
    }

    public ResponseEntity<ResumeDto> createResume(ResumeDto resumeDto) {
        if (resumeDto.getName() == null || resumeDto.getName().isBlank()) {
            return ResponseEntity.badRequest().body(resumeDto);
        }

        if (resumeDto.getSalary() < 0) {
            return ResponseEntity.badRequest().body(resumeDto);
        }

        Resume resume = Resume.builder()
                .applicantId(1L)
                .name(resumeDto.getName())
                .categoryId(resumeDto.getCategoryId())
                .salary(resumeDto.getSalary())
                .isActive(resumeDto.isActive())
                .createdDate(LocalDateTime.now())
                .build();

        String sqltype = "select account_type from users where id = ?";
        String typename = jdbcTemplate.queryForObject(sqltype, String.class, resume.getApplicantId());
        if (typename == null || !typename.equalsIgnoreCase("applicant")) {
            return ResponseEntity.badRequest().body(resumeDto);
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
            jdbcTemplate.update(sql, resumeId);
            return HttpStatus.resolve(410);
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

            String sql = "update resumes set name = ?, category_id = ?, salary = ?, is_active = ? where id = ?";
            jdbcTemplate.update(sql,resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.isActive(), resumeId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resumeDto);
        }
}