package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import kg.attractor.jobsearch.enums.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    @Pattern(regexp = "^\\p{L}+$", message = "Имя должно содержать только буквы")
    @NotNull(message = "Имя не должно быть пустым")
    @Size(min = 1,max = 20, message = "символы от 1 до 20")
    private String name;
    @NotNull(message = "Фамилия не должна быть пустой")
    @Pattern(regexp = "^\\p{L}+$", message = "Имя должно содержать только буквы")
    @Size(min = 1,max = 20, message = "символы от 1 до 20")
    private String surname;
    @NotNull(message = "возраст не может быть 0")
    @Size(min = 14,max = 120, message = "возраст от 14 до 120")
    private int age;
    @Email(message = "неправильный формат")
    private String email;
//    @Pattern(regexp = "\\+996\\s?\\d{3}\\s?\\d{3}\\s?\\d{3}", message = "Некорректный номер. Формат: +996 000 000 000")

    private String phone;
    private AccountType accountType;

}