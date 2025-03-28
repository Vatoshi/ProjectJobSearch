package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
