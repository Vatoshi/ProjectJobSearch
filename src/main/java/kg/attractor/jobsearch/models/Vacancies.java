package kg.attractor.jobsearch.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Vacancies {
    private String name;
    private String description;
    private int categoryId;
    private double salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private int authorId;
    private LocalDate createdDate;
    private LocalDate updatedTime;
    private int id;
}
