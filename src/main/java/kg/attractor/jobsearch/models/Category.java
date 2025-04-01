package kg.attractor.jobsearch.models;

import lombok.Data;

@Data
public class Category {
    private String name;
    private Long parentId;
    private Long id;
}
