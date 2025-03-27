package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import kg.attractor.jobsearch.enums.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    @NotNull(message = "Имя не должно быть пустым")
    @Size(min = 3,max = 20)
    private String name;
    @NotNull(message = "Фамилия не должна быть пустой")
    private String surname;
    @NotNull
    @Size(min = 14,max = 120)
    private int age;
    @Email
    private String email;
    @Pattern(regexp = "\\+996\\s?\\d{3}\\s?\\d{3}\\s?\\d{3}", message = "Некорректный номер. Формат: +996 000 000 000")
    private String phone;
    private AccountType accountType;

}