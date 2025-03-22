//package kg.attractor.jobsearch.controlers;
//
//import kg.attractor.jobsearch.servise.DBService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.SQLException;
//
//@RestController
//@RequiredArgsConstructor
//public class DBconnect {
//    private final DBService dbService;
//
//    @GetMapping
//    public ResponseEntity<String> getDB() throws SQLException {
//        return new ResponseEntity<>(dbService.openConnection(), HttpStatus.OK);
//    }
//}
