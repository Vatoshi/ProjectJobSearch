package kg.attractor.jobsearch.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageDto {
    private MultipartFile image;
    private Long userId;
}
