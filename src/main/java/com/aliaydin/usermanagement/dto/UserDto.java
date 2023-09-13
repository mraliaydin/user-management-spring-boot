package com.aliaydin.usermanagement.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;

    @Schema(
            description = "User First Name"
    )
    // User first name should not be empty or null
    @NotEmpty(message = "User first name should not be empty or null")
    private String firstName;

    // User last name should not be empty or null
    @NotEmpty
    private String lastName;

    // User email should not be empty or null
    // Email should be valid.
    @NotEmpty
    @Email
    private String email;
}
