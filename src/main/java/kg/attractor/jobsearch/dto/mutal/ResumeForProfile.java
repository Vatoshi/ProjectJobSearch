package kg.attractor.jobsearch.dto.mutal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForProfile {
    private Long id;
    private String name;
    private Date updateTime;
}
