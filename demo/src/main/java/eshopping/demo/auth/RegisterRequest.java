package eshopping.demo.auth;
import lombok.Data;
import lombok.Builder;

import eshopping.demo.user.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterRequest {
private String name;
private String email;
private String password;
private Role role;

}
