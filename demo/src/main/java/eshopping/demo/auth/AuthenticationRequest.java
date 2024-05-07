package eshopping.demo.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor



public class AuthenticationRequest {
        @Email(message = "Please provide a valid email address")
        private String email;

        @Size(min = 8, message = "Password must be at least 8 characters long")
        private String password;
}
