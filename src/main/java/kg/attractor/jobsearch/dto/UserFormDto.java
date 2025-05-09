package kg.attractor.jobsearch.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFormDto {
    @Pattern(regexp = "^\\p{L}+$", message = "{valid.asd}")
    @NotNull
    @Size(min = 1,max = 20)
    private String name;
    @NotNull
    @Pattern(regexp = "^\\p{L}+$", message = "{valid.asd}")
    @Size(min = 1,max = 20)
    private String surname;
    @NotNull
    @Min(value = 14, message = "{valid.14}")
    @Max(value = 120, message = "{valid.120}")
    private Integer age;
    @NotEmpty
    @Email(message = "{valid.email}")
    private String email;
    @Pattern(regexp = "^(?=.*\\d).{5,}$", message = "{valid.name}")
    private String password;
    private String avatar;
    @NotNull
    @Pattern(regexp = "^\\d{10}$", message = "{valid.number}")
    private String phoneNumber;
    @NotNull(message = "{valid.acctype}")
    private Long roleId;
}
