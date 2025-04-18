package site.aviralgupta.video_rental_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import site.aviralgupta.video_rental_system.util.enums.UserRoleEnum;

@Data
public class UserRegisterDto {

    @NotNull
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private UserRoleEnum role = UserRoleEnum.CUSTOMER;
}
