package kg.attractor.jobsearch.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Messages {
    private int respondedApplicantsId;
    private String content;
    private LocalDate timestamp;
    private int id;
}
