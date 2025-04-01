package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoriesDto {
    String name;
    private Integer parentId;
}
