package site.aviralgupta.video_rental_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDto {

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;
}
