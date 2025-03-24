package kg.attractor.jobsearch.models;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Message {
    private Long respondedApplicantsId;
    private String content;
    private LocalDateTime timestamp;
    private Long id;
}
