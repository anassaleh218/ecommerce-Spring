package eshopping.demo.auth;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor



public class AuthenticationRequest {
        private String email;
        private String password;
}
