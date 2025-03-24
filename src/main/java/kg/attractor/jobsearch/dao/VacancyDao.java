package kg.attractor.jobsearch.dao;
import kg.attractor.jobsearch.models.User;
import kg.attractor.jobsearch.models.Vacancy;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;


@Component
@RequiredArgsConstructor
@Builder
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getRespondedUsersOnVacancy(Long vacancyId) {
        String sql1 = "SELECT resume_id FROM responded_applicants WHERE vacancy_id = ?";
        List<Long> resumeIds = jdbcTemplate.queryForList(
                sql1,
                Long.class,
                vacancyId
        );

        if (resumeIds.isEmpty()) {
            return Collections.emptyList();
        }

        String sql2 = "SELECT DISTINCT applicant_id FROM resumes WHERE id IN (" +
                String.join(",", Collections.nCopies(resumeIds.size(), "?")) + ")";

        List<Long> applicantIds = jdbcTemplate.queryForList(
                sql2,
                Long.class,
                resumeIds.toArray()
        );

        if (applicantIds.isEmpty()) {
            return Collections.emptyList();
        }

        String sql3 = "SELECT * FROM users WHERE id IN (" +
                String.join(",", Collections.nCopies(applicantIds.size(), "?")) + ")";

        return jdbcTemplate.query(
                sql3,
                new BeanPropertyRowMapper<>(User.class),
                applicantIds.toArray()
        );
    }
    public List<Vacancy> getAllVacancies() {
        String sql = "SELECT id FROM vacancies";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> findByCategory(String name) {
        String sqlCategoryIds = "SELECT id FROM categories WHERE name = ?";
        List<Integer> categoryIds = jdbcTemplate.queryForList(sqlCategoryIds, Integer.class, name
        );

        if (categoryIds.isEmpty()) {
            return Collections.emptyList();
        }

        String placeholders = String.join(",", Collections.nCopies(categoryIds.size(), "?"));
        String sqlVacancies = String.format(
                "SELECT * FROM vacancies WHERE category_id IN (%s)", placeholders);

        return jdbcTemplate.query(sqlVacancies, new BeanPropertyRowMapper<>(Vacancy.class), categoryIds.toArray());
    }

    public List<Vacancy> getVacanciesResponses(Long userId) {
        String checkResumeSql = "SELECT COUNT(*) FROM resumes WHERE applicant_id = ?";
        Integer resumeCount = jdbcTemplate.queryForObject(checkResumeSql, Integer.class, userId);
        if (resumeCount == 0) throw new IllegalArgumentException("У пользователя нет резюме");

        String getResumesSql = "SELECT id FROM resumes WHERE applicant_id = ?";
        List<Long> resumeIds = jdbcTemplate.queryForList(getResumesSql, Long.class, userId);

        if (resumeIds.isEmpty()) {
            return Collections.emptyList();
        }

        String sql = "SELECT * " +
                        "FROM vacancies v " +
                        "INNER JOIN responded_applicants ra ON v.id = ra.vacancy_id " +
                        "WHERE ra.resume_id IN (" + String.join(",", Collections.nCopies(resumeIds.size(), "?")) + ")";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), resumeIds.toArray());
    }
}