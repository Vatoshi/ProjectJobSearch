package kg.attractor.jobsearch.models;

import lombok.Data;

@Data
public class Categories {
    private String name;
    private int parentId;
    private int id;
}
