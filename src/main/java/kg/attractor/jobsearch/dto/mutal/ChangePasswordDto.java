package kg.attractor.jobsearch.dto.mutal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordDto {
    @NotEmpty(message = "{valid.ps}")
    private String password;
    @NotNull(message = "{valid.newps}")
    @Pattern(regexp = "^(?=.*\\d).{5,}$", message = "{valid.name}")
    private String newPassword;
    @NotNull(message = "{valid.confps}")
    private String confirmPassword;
}
