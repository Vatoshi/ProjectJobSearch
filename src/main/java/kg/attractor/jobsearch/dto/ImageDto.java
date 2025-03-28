package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageDto {
    @NotNull(message = "image not be upload")
    private MultipartFile image;
    private Long userId;
}
