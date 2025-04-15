package kg.attractor.jobsearch.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEditDto {
    @Pattern(regexp = "^(|\\p{L}+)$", message = "Имя должно содержать только буквы")
    @Size(max = 20, message = "Максимум 20 символов")
    private String name;
    @Pattern(regexp = "^(|\\p{L}+)$", message = "Фамилия должна содержать только буквы")
    @Size(max = 20, message = "Максимум 20 символов")
    private String surname;
    @Min(value = 14, message = "Минимальный возраст 14 лет")
    @Max(value = 120, message = "Максимальный возраст 120 лет")
    private Integer age;
    @Email(message = "Неправильный формат email")
    private String email;
    @Pattern(regexp = "^(|(?=.*\\d).{5,})$", message = "содержать минимум 5 символов и хотя бы одну цифру")
    private String password;
    private String avatar;
    @Pattern(regexp = "^(|\\d{10})$", message = "Телефон должен содержать 10 цифр")
    private String phoneNumber;
}