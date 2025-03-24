package kg.attractor.jobsearch.mapper;
import kg.attractor.jobsearch.models.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("ID"));
        user.setName(rs.getString("NAME"));
        user.setSurname(rs.getString("SURNAME"));
        user.setAge(rs.getInt("AGE"));
        user.setEmail(rs.getString("EMAIL"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        user.setAvatar(rs.getString("AVATAR"));
        user.setAccountType(rs.getString("ACCOUNT_TYPE"));
        return user;
    }

}
