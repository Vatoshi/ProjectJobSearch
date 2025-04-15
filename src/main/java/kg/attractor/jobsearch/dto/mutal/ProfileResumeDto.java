package kg.attractor.jobsearch.dto.mutal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResumeDto {
    private Long id;
    private String name;
    private LocalDate updated;
}
