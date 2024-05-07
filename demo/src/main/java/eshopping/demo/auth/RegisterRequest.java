package eshopping.demo.auth;
import lombok.Data;
import lombok.Builder;

import eshopping.demo.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
@NotBlank(message = "Please provide a name")
private String name;

@Email(message = "Please provide a valid email address")
private String email;

@Size(min = 8, message = "Password must be at least 8 characters long")
private String password;

private Role role;
}
