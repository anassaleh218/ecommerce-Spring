package eshopping.demo.auth;

import java.util.Optional;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import eshopping.demo.config.JwtService;
import eshopping.demo.user.User;
import eshopping.demo.user.UserRepository;

@Aspect
@Component
public class AuthorizationAspect {
    public User user;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    @Before("execution(* eshopping.demo.order.OrderController.*(..)) && args(.., authHeader)")
    public void interceptControllerMethods(String authHeader) {
        final String jwt = authHeader.substring(7);
        final Integer userId = jwtService.extractUserId(jwt);

        Optional<User> userOptional = userRepository.findById(userId);
        this.user = userOptional.get();
    }
}
