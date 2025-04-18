package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.dto.EducationInfoDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

@Repository
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

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

    private void createEducation(Long resumeId, EducationInfoDto e) {
        String sqlEducation = "insert into education_info(resume_id, institution, program, start_date, end_date,degree) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sqlEducation, resumeId, e.getInstitution(), e.getProgram(), e.getStartDate(), e.getEndDate(), e.getDegree());
    }

    private void createWorkExperience(Long resumeId, WorkExperienceInfoDto w) {
        String sql = "insert into work_experience_info(resume_id, years, company_name,position,responsibilities) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, resumeId, w.getYears(), w.getCompanyName(), w.getPosition(), w.getResponsibilities());
    }

    private void createSingleEducation(Long resumeId) {
        String sqlEducation = "insert into education_info(resume_id) values(?)";
        jdbcTemplate.update(sqlEducation, resumeId);
    }

    private void createSingleWorkExperience(Long resumeId) {
        String sqlWork = "insert into work_experience_info(resume_id) values(?)";
        jdbcTemplate.update(sqlWork, resumeId);
    }
}
