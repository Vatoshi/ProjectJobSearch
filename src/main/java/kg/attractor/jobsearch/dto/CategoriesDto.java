package kg.attractor.jobsearch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoriesDto {
    String name;
    private int parentId;
}
