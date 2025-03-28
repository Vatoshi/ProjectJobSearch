package kg.attractor.jobsearch.dao;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Builder
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email)
                )
        );
    }

    public List<User> findByName(String name) {
        String sql = "SELECT * FROM users WHERE name ILIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + name + "%");
    }

    public List<User> findByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone_number = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), phone);
    }

    public void save(String filename, Long userId) {
        String finduser = "select id from users where id = ?";
            List<Integer> id = jdbcTemplate.queryForList(finduser, Integer.class, userId);
        if (id.isEmpty()) {
            throw new NotFound("User for upload file not found");
        }
        String sql = "update users set avatar = ? where id = ?";
        jdbcTemplate.update(sql, filename, userId);
    }

}

