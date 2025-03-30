package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exeptions.EntityForDeleteNotFound;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.UserStatusExeption;
import kg.attractor.jobsearch.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Resume> findResumeById(Long resumeId) {
        String sql = "SELECT * FROM resumes WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), resumeId)));
    }

    public List<Resume> findByCategory(String name) {
        String sqlCategoryIds = "SELECT id FROM categories WHERE name = ?";
        List<Integer> categoryIds = jdbcTemplate.queryForList(sqlCategoryIds, Integer.class, name
        );

        if (categoryIds.isEmpty()) {
            throw new NotFound("resume by this category does not exist");
        }

        String sqlResumes = "SELECT * FROM resumes WHERE category_id IN (:ids)";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", categoryIds);

        return namedParameterJdbcTemplate.query(sqlResumes, parameters, new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> findByUser(Long userId) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public ResumeDto createResume(ResumeDto resumeDto, Resume resume) {
        String sqltype = "select account_type from users where id = ?";
        String typename = jdbcTemplate.queryForObject(sqltype, String.class, resume.getApplicantId());

        if (typename == null || !typename.equalsIgnoreCase("applicant")) {
            throw new UserStatusExeption("wrong user status");
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO resumes(applicant_id, name, category_id, salary, is_active, created_date) VALUES (?, ?, ?, ?, ?, ?)",
                    new String[]{"ID"}
            );
            ps.setLong(1, resume.getApplicantId());
            ps.setString(2, resume.getName());
            ps.setInt(3, resume.getCategoryId());
            ps.setDouble(4, resume.getSalary());
            ps.setBoolean(5, resume.isActive());
            ps.setTimestamp(6, Timestamp.valueOf(resume.getCreatedDate()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("Failed to retrieve resume ID");
        }
        Long resumeId = key.longValue();
        updateEducation(resumeId);
        updateWorkExperience(resumeId);
        return resumeDto;
    }

    public void updateEducation(Long resumeId) {
        String sqlEducation = "insert into education_info(resume_id) values(?)";
        jdbcTemplate.update(sqlEducation, resumeId);
    }

    public void updateWorkExperience(Long resumeId) {
        String sqlWork = "insert into work_experience_info(resume_id) values(?)";
        jdbcTemplate.update(sqlWork, resumeId);
    }

    public HttpStatus deleteResume(Long resumeId) {
        String sql = "delete from resumes where id = ?";
            int resumefound = jdbcTemplate.update(sql, resumeId);

            if (resumefound == 0) {
                throw new EntityForDeleteNotFound("resume not found");
            }
            return HttpStatus.ACCEPTED;
        }

        public String getName(Long resumeId) {
            return jdbcTemplate.queryForObject("select name from resumes where id = ?", String.class,resumeId);
        }

        public int getCategoryId(Long resumeId) {
            return jdbcTemplate.queryForObject("select category_id from resumes where id = ?", Integer.class, resumeId);
        }

        public Double getSalary(Long resumeId) {
            return jdbcTemplate.queryForObject("select salary from resumes where id = ?", Double.class, resumeId);
        }

        public LocalDateTime getCreatedDate(Long resumeId) {
         return jdbcTemplate.queryForObject("select created_date from resumes where id = ?", LocalDateTime.class, resumeId);
        }

        public ResponseEntity<ResumeDto> updateResume(ResumeDto resumeDto, Long resumeId) {
            String sql = "update resumes set name = ?, category_id = ?, salary = ?, is_active = ?, update_time = ? where id = ?";
            jdbcTemplate.update(sql,resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.isActive(), resumeDto.getUpdateTime(), resumeId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resumeDto);
        }
}
