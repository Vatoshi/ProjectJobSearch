package kg.attractor.jobsearch.dao;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.enums.AccountType;
import kg.attractor.jobsearch.exeptions.NotFound;
import kg.attractor.jobsearch.models.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id)
                )
        );
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

    public void createAcc(User u) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(u.getPassword());
        String accountType = "";
        if (u.getAccountType() == null) {
            accountType = "none";
        } else {
            accountType = u.getAccountType().name();
        }
        String sql = "insert into users (name, surname, age, email, password, phone_number, avatar, account_type, enabled, role_id)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,u.getName(),u.getSurname(),u.getAge(),u.getEmail(),hashedPassword,u.getPhoneNumber(),u.getAvatar(),accountType,u.getEnabled(),u.getRoleId());
    }

    public void updateUser(UserEditDto u, Long userId) {
        String sql = "update users set name = ?," +
                " surname = ?," +
                " age = ?," +
                " email = ?," +
                " password = ?," +
                " avatar = ?," +
                " phone_number = ?" +
                " where id = ?";
        jdbcTemplate.update(sql,u.getName(),
                u.getSurname(),
                u.getAge(),
                u.getEmail(),
                u.getPassword(),
                u.getAvatar(),
                u.getPhoneNumber(),
                userId);
    }

    public String getExistEmail(String email) {
        String sql = "select email from users where email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

}

