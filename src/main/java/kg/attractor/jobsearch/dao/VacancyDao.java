package kg.attractor.jobsearch.dao;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.dto.VacancyEditDto;
import kg.attractor.jobsearch.dto.mutal.ProfileResumeDto;
import kg.attractor.jobsearch.dto.mutal.ProfileVacancyDto;
import kg.attractor.jobsearch.exeptions.EntityForDeleteNotFound;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.exeptions.ResumeFromUserNotFound;
import kg.attractor.jobsearch.exeptions.UserStatusExeption;
import kg.attractor.jobsearch.models.User;
import kg.attractor.jobsearch.models.Vacancy;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
@Builder
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public Long userId(String username) {
        String sql = "select id from users where email = ?";
        return jdbcTemplate.queryForObject(sql,Long.class, username);
    }

    public List<ProfileVacancyDto> getVacancyByUser(Long userId) {
        String sql = "SELECT name, update_time FROM vacancies WHERE author_id = ?";
        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new ProfileVacancyDto(
                        rs.getString("name"),
                        rs.getObject("update_time", LocalDate.class)
                ),
                userId
        );
    }

    public List<User> getRespondedUsersOnVacancy(Long vacancyId) {
        String sql1 = "SELECT resume_id FROM responded_applicants WHERE vacancy_id = ?";
        List<Long> resumeIds = jdbcTemplate.queryForList(
                sql1,
                Long.class,
                vacancyId
        );

        if (resumeIds.isEmpty()) {
            throw new NotFound("resumes not found");
        }

        String sql2 = "SELECT DISTINCT applicant_id FROM resumes WHERE id IN (" +
                String.join(",", Collections.nCopies(resumeIds.size(), "?")) + ")";

        List<Long> applicantIds = jdbcTemplate.queryForList(
                sql2,
                Long.class,
                resumeIds.toArray()
        );

        if (applicantIds.isEmpty()) {
            throw new NotFound("applicants not found");
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
        String sql = "SELECT * FROM vacancies";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> findByCategory(String name) {
        String sqlCategoryIds = "SELECT id FROM categories WHERE name = ?";
        List<Integer> categoryIds = jdbcTemplate.queryForList(sqlCategoryIds, Integer.class, name
        );

        if (categoryIds.isEmpty()) {
            throw new NotFound("vacancy not found");
        }

        String placeholders = String.join(",", Collections.nCopies(categoryIds.size(), "?"));
        String sqlVacancies = String.format(
                "SELECT * FROM vacancies WHERE category_id IN (%s)", placeholders);

        return jdbcTemplate.query(sqlVacancies, new BeanPropertyRowMapper<>(Vacancy.class), categoryIds.toArray());
    }

    public List<Vacancy> getVacanciesResponses(Long userId) {
        String checkResumeSql = "SELECT COUNT(*) FROM resumes WHERE applicant_id = ?";
        Integer resumeCount = jdbcTemplate.queryForObject(checkResumeSql, Integer.class, userId);
        if (resumeCount == 0) throw new ResumeFromUserNotFound();

        String getResumesSql = "SELECT id FROM resumes WHERE applicant_id = ?";
        List<Long> resumeIds = jdbcTemplate.queryForList(getResumesSql, Long.class, userId);

        if (resumeIds.isEmpty()) {
            throw new NotFound("resume not found");
        }

        String sql = "SELECT * " +
                        "FROM vacancies v " +
                        "INNER JOIN responded_applicants ra ON v.id = ra.vacancy_id " +
                        "WHERE ra.resume_id IN (" + String.join(",", Collections.nCopies(resumeIds.size(), "?")) + ")";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), resumeIds.toArray());
    }



    public VacancyDto createVacancy(VacancyDto vacancyDto, Vacancy vacancy) {
        String sqltype = "select account_type from users where id = ?";
        String typename = jdbcTemplate.queryForObject(sqltype, String.class, vacancy.getAuthorId());

        if (typename == null || !typename.equalsIgnoreCase("employer")) {
            throw new UserStatusExeption("wrong user status");
        }

        String sql = "insert into vacancies (name,description,category_id,salary,exp_from, exp_to,is_active,author_id,created_date,update_time)" +
                " values (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,vacancy.getName(),vacancy.getDescription(),vacancy.getCategoryId(),vacancy.getSalary(),vacancy.getExpFrom(),vacancy.getExpTo(),vacancy.getIsActive(),vacancy.getAuthorId(),vacancy.getCreatedDate(),vacancy.getUpdateTime());
        return vacancyDto;
    }

    public HttpStatus deleteVacancy(Long vacancyId) {
        String sql = "delete from vacancies where id = ?";
        int vacanciesfound = jdbcTemplate.update(sql, vacancyId);

        if (vacanciesfound == 0) {
            throw new EntityForDeleteNotFound("vacancies not found");
        }
        return HttpStatus.ACCEPTED;
    }

    public Optional<Vacancy> getVacancy(Long vacancyId) {
        return Optional.ofNullable(DataAccessUtils.singleResult(jdbcTemplate.query("select * from vacancies where id = ?", new BeanPropertyRowMapper<>(Vacancy.class), vacancyId)));
    }

    public VacancyEditDto updateVacancy(VacancyEditDto vacancyDto, Long vacancyId) {
        String sql = "update vacancies set name = ?, description = ?, category_id = ?, salary = ?, exp_from = ?, exp_to = ?, is_active = ?, update_time = ? where id = ?";
        jdbcTemplate.update(sql,vacancyDto.getName(), vacancyDto.getDescription(),vacancyDto.getCategoryId(),vacancyDto.getSalary(), vacancyDto.getExpFrom(), vacancyDto.getExpTo(), vacancyDto.getIsActive(), vacancyDto.getUpdateTime(), vacancyId);
        return vacancyDto;
    }
}