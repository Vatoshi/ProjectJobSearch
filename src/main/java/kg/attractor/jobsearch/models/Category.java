package kg.attractor.jobsearch.models;

import lombok.Data;

@Data
public class Category {
    private String name;
    private int parentId;
    private Long id;
}
