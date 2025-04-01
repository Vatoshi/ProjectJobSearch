package kg.attractor.jobsearch.dao;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Builder
public class WorkExperienceDao {
    private final JdbcTemplate jdbcTemplate;


}
