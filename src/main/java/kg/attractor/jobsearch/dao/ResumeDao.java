package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.models.Category;
import kg.attractor.jobsearch.models.Resume;
import kg.attractor.jobsearch.models.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Resume> findByCategory(String name) {
        String sqlCategoryIds = "SELECT id FROM categories WHERE name = ?";
        List<Integer> categoryIds = jdbcTemplate.queryForList(sqlCategoryIds, Integer.class, name
        );

        if (categoryIds.isEmpty()) {
            return Collections.emptyList();
        }

        String placeholders = String.join(",", Collections.nCopies(categoryIds.size(), "?"));
        String sqlVacancies = String.format(
                "SELECT * FROM resumes WHERE category_id IN (%s)", placeholders);

        return jdbcTemplate.query(sqlVacancies, new BeanPropertyRowMapper<>(Resume.class), categoryIds.toArray());
    }

    public List<Resume> findByUser(Long userId) {
        String sql = "SELECT * FROM resumes WHERE applicant_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }
}
