package kg.attractor.jobsearch.dto;

import kg.attractor.jobsearch.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String avatar;
    private String phoneNumber;
    private AccountType accountType;
}