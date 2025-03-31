package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import kg.attractor.jobsearch.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFormDto {
    @Pattern(regexp = "^\\p{L}+$", message = "Имя должно содержать только буквы")
    @NotNull(message = "Имя не должно быть пустым")
    @Size(min = 1,max = 20, message = "символы от 1 до 20")
    private String name;
    @NotNull(message = "Фамилия не должна быть пустой")
    @Pattern(regexp = "^\\p{L}+$", message = "Имя должно содержать только буквы")
    @Size(min = 1,max = 20, message = "символы от 1 до 20")
    private String surname;
    @NotNull(message = "возраст не может быть 0")
    @Min(value = 14, message = "возраст должен быть от 14")
    @Max(value = 120, message = "слишком стары")
    private int age;
    @Email(message = "неправильный формат")
    private String email;
    @Pattern(regexp = "^(?=.*\\d).{5,}$", message = "длина должна быть минимум 5 символов и иметь хотя бы одну цифру")
    private String password;
    private String avatar;
    @Pattern(regexp = "^\\d{10}$\n", message = "10 цифр")
    private String phone;
    private AccountType accountType;
}
