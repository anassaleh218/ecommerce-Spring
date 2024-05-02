package eshopping.demo.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eshopping.demo.cart.Cart;
import eshopping.demo.cart.cartRepository;
import eshopping.demo.config.JwtService;
import eshopping.demo.user.UserRepository;
import lombok.RequiredArgsConstructor;

import eshopping.demo.user.User;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository userRepository;
    private final cartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    Des des = new Des();

    public AuthenticationResponse register(RegisterRequest request) throws Exception {

        String eName = des.encrypt(request.getName());
        String eEmail = des.encrypt(request.getEmail());
        User user = User.builder()
                .name(eName)
                .email(eEmail)
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        Cart cart = Cart.builder()
                .user(user)
                .build();

        cartRepository.save(cart);

        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(request.getRole().name())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {
        String eEmail = des.encrypt(request.getEmail());
        System.out.println(eEmail);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        eEmail,
                        request.getPassword()));
        var user = userRepository.findByEmail(eEmail)
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .role(user.getRole().name())
                .build();
    }

}
