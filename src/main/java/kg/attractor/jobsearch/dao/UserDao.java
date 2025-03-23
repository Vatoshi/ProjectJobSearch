package kg.attractor.jobsearch.dao;

import kg.attractor.jobsearch.mapper.UserMapper;
import kg.attractor.jobsearch.models.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Builder
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List<User> getUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public Optional<User> getUserById(int id) {
        String sql = "select * from users where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class))
                )
        );
    }
}

