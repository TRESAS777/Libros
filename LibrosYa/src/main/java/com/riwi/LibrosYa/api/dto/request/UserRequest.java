package com.riwi.LibrosYa.api.dto.request;

import org.hibernate.validator.constraints.Length;

import com.riwi.LibrosYa.utils.enums.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequest {
    
    @NotBlank(message = "The username is required")
    @Length(min = 1,max = 50, message = "Invalid lenght for the username (max 50)")
    private String username;

    @NotBlank(message = "The password is required")
    @Length(min = 1,max = 100, message = "Invalid lenght for the password (max 100)")
    private String password;

    @NotBlank(message = "The email is required")
    @Length(min = 1,max = 100, message = "Invalid lenght for the email (max 100)")
    private String email;

    @NotBlank(message = "The name is required")
    @Length(min = 1,max = 100, message = "Invalid lenght for the name (max 100)")
    private String fullName;

    @NotNull(message = "The role is required")
    private UserRole role;

}
