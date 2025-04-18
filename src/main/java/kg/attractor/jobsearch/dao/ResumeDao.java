package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.dto.mutal.ProfileResumeDto;
import kg.attractor.jobsearch.exeptions.EntityForDeleteNotFound;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.UserStatusExeption;
import kg.attractor.jobsearch.models.EducationInfo;
import kg.attractor.jobsearch.models.Resume;
import kg.attractor.jobsearch.models.WorkExperienceInfo;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public String getUserName(Long userId) {
        String sql = "select name from users where id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, userId);
    }

    public List<Resume> getAllResumes() {
        String sql = "select * from resumes";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Long userId(String username) {
        String sql = "select id from users where email = ?";
        return jdbcTemplate.queryForObject(sql,Long.class, username);
    }

    public List<ProfileResumeDto> getResumeByUser(Long userId) {
        String sql = "SELECT id, name, update_time FROM resumes WHERE applicant_id = ?";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new ProfileResumeDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getObject("update_time", LocalDate.class)
                ),
                userId
        );
    }

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

    public List<Resume> findByUser(Long userId, Long resumeId) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ? and id = ?";
        try {
            List<Resume> a = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId, resumeId);
            if (a.isEmpty()) {
                throw new NotFound("resume not fount");
            }
        } catch (Exception e) {
            throw new NotFound("resume not fount");
        }
        return null;
    }

    public ResumeDto createResume(ResumeDto resumeDto, Resume resume) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO resumes(applicant_id, name, category_id, salary, is_active, created_date) VALUES (?, ?, ?, ?, ?, ?)",
                    new String[]{"ID"}
            );
            ps.setLong(1, resume.getUser().getId());
            ps.setString(2, resume.getName());
            ps.setLong(3, resume.getCategory() != null ? resume.getCategory().getId() : 1);
            ps.setDouble(4, resume.getSalary() != null ? resume.getSalary() : 0);
            ps.setBoolean(5, resume.getIsActive() != null ? resume.getIsActive() : false);
            ps.setTimestamp(6, Timestamp.valueOf(resume.getCreatedDate().atStartOfDay()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("Failed to retrieve resume ID");
        }
        Long resumeId = key.longValue();

        if (resumeDto.getEducationInfo() == null) {
            createSingleEducation(resumeId);
        } else {
            EducationInfoDto educDto = resumeDto.getEducationInfo().getFirst();
            createEducation(resumeId,educDto);
        }

        if (resumeDto.getWorkExperienceInfo() == null) {
            createSingleWorkExperience(resumeId);
        } else {
            WorkExperienceInfoDto workDto = resumeDto.getWorkExperienceInfo().getFirst();
            createWorkExperience(resumeId,workDto);
        }

        return resumeDto;
    }

    public void createEducation(Long resumeId, EducationInfoDto e) {
        String sqlEducation = "insert into education_info(resume_id, institution, program, start_date, end_date,degree) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sqlEducation, resumeId, e.getInstitution(), e.getProgram(), e.getStartDate(), e.getEndDate(), e.getDegree());
    }

    public void createWorkExperience(Long resumeId, WorkExperienceInfoDto w) {
        String sql = "insert into work_experience_info(resume_id, years, company_name,position,responsibilities) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, resumeId, w.getYears(), w.getCompanyName(), w.getPosition(), w.getResponsibilities());
    }

    public void createSingleEducation(Long resumeId) {
        String sqlEducation = "insert into education_info(resume_id) values(?)";
        jdbcTemplate.update(sqlEducation, resumeId);
    }

    public void createSingleWorkExperience(Long resumeId) {
        String sqlWork = "insert into work_experience_info(resume_id) values(?)";
        jdbcTemplate.update(sqlWork, resumeId);
    }

    public EducationInfoDto getEducationInfo(Long resumeId) {
        String sql = "select resume_id, institution, program, start_date, end_date, degree from education_info where resume_id = ?";
        List<EducationInfoDto> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EducationInfoDto.class), resumeId);
        return results.isEmpty() ? new EducationInfoDto() : results.get(0);
    }

    public WorkExperienceInfoDto getWorkExperienceInfo(Long resumeId) {
        String sql = "select resume_id, years, company_name, position, responsibilities from work_experience_info where resume_id = ?";
        List<WorkExperienceInfoDto> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(WorkExperienceInfoDto.class), resumeId);
        return results.isEmpty() ? new WorkExperienceInfoDto() : results.get(0);
    }

    public void updateWorkExperienceInfo(Long resumeId, WorkExperienceInfoDto w) {
        String sql = "update work_experience_info set years = ?, company_name = ?, position = ?, responsibilities = ? where resume_id = ?";
        jdbcTemplate.update(sql,w.getYears(), w.getCompanyName(), w.getPosition(), w.getResponsibilities(), resumeId);
    }

    public void updateEducationInfo(Long resumeId, EducationInfoDto e) {
        String sql = "update education_info set institution = ?, program = ?, start_date = ?, end_date = ? where resume_id = ?";
        jdbcTemplate.update(sql, e.getInstitution(), e.getProgram(), e.getStartDate(), e.getEndDate(), resumeId);
    }

    public HttpStatus deleteResume(Long resumeId) {
        String sql = "delete from resumes where id = ?";
            int resumefound = jdbcTemplate.update(sql, resumeId);

            if (resumefound == 0) {
                throw new EntityForDeleteNotFound("resume not found");
            }
            return HttpStatus.ACCEPTED;
        }

        public ResumeDto updateResume(ResumeDto resumeDto, Long resumeId) {
            String sql = "update resumes set name = ?, category_id = ?, salary = ?, is_active = ?, update_time = ? where id = ?";
            jdbcTemplate.update(sql,resumeDto.getName(), resumeDto.getCategoryId(), resumeDto.getSalary(), resumeDto.getIsActive(), resumeDto.getUpdateTime(), resumeId);
            return resumeDto;
        }

    public void updatetime(Long resumeId) {
        String sql = "update resumes set update_time = ? where id = ?";
        jdbcTemplate.update(sql, LocalDateTime.now(), resumeId);
    }
}
