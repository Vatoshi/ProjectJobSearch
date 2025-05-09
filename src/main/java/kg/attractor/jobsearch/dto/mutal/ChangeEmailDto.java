package kg.attractor.jobsearch.dto.mutal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangeEmailDto {
    @NotEmpty(message = "{valid.ps}")
    private String password;
    @NotEmpty
    @Email
    private String newEmail;
    @NotEmpty
    @Email
    private String confirmEmail;
}
