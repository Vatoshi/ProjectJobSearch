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
    @Pattern(regexp = "^(|\\p{L}+)$", message = "{valid.asd}")
    @Size(max = 20, message = "{valid.20}")
    private String name;
    @Pattern(regexp = "^(|\\p{L}+)$", message = "{valid.asd2}")
    @Size(max = 20, message = "{valid.20}")
    private String surname;
    @Min(value = 14, message = "{valid.14}")
    @Max(value = 120, message = "{valid.120}")
    private Integer age;
    @Email(message = "{valid.email}")
    private String email;
    @Pattern(regexp = "^(|(?=.*\\d).{5,})$", message = "{valid.name}")
    private String password;
    private String avatar;
    @Pattern(regexp = "^(|\\d{10})$", message = "{valid.number}")
    private String phoneNumber;
}