package kg.attractor.jobsearch.dto.mutal;


import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class passwordChangeDto {
    @Pattern(regexp = "^(?=.*\\d).{5,}$", message = "длина должна быть минимум 5 символов и иметь цифру")
    private String newPassword;
    private String confirmPassword;
}
