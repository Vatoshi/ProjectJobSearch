//package kg.attractor.jobsearch.servise;
//
//import org.springframework.stereotype.Service;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//import static org.h2.util.JdbcUtils.getConnection;
//
//@Service
//public class DBService {
//    private Connection conn;
//
//    public DBService() {
//        try {
//            init();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Connection getConnection() throws SQLException {
//        String url = "jdbc:postgresql://localhost:5432/postgres?user=fred&password=123321";
//        return DriverManager.getConnection(url);
//    }
//
//    private void init() throws SQLException {
//        conn = getConnection();
//    }
//
//    private void close() throws SQLException {
//        conn.close();
//    }
//
//    public String openConnection(){
//        try {
//            init();
//            return "Connection Established";
//        } catch (SQLException e) {
//            return e.getMessage();
//        }
//    }
//}
