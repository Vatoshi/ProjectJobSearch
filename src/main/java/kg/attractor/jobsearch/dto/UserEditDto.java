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
public class UserEditDto {
    @Pattern(regexp = "^\\p{L}+$", message = "Имя должно содержать только буквы")
    @Size(min = 1,max = 20, message = "символы от 1 до 20")
    private String name;
    @Pattern(regexp = "^\\p{L}+$", message = "Имя должно содержать только буквы")
    @Size(min = 1,max = 20, message = "символы от 1 до 20")
    private String surname;
    private Integer age;
    @Email(message = "неправильный формат")
    private String email;
    @Pattern(regexp = "^(?=.*\\d).{5,}$", message = "длина должна быть минимум 5 символов и иметь хотя бы одну цифру")
    private String password;
    private String avatar;
    @Pattern(regexp = "^\\d{10}$", message = "10 цифр")
    private String phoneNumber;
    private AccountType accountType;
}
