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
    @NotEmpty(message = "введите пароль")
    private String password;
    @NotNull(message = "введите новый пароль")
    @Pattern(regexp = "^(?=.*\\d).{5,}$", message = "длина должна быть минимум 5 символов и иметь хотя бы одну цифру")
    private String newPassword;
    @NotNull(message = "подтвердите новый пароль")
    private String confirmPassword;
}
