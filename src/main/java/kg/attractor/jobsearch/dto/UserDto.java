package kg.attractor.jobsearch.dto;

import kg.attractor.jobsearch.enums.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
    private AccountType accountType;

}