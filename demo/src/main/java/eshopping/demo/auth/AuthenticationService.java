package eshopping.demo.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import eshopping.demo.config.JwtService;
import eshopping.demo.user.UserRepository;
import eshopping.demo.cart.Cart;
import eshopping.demo.cart.CartRepository;

import lombok.RequiredArgsConstructor;

import eshopping.demo.user.User;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
                
        userRepository.save(user);

        var cart = Cart.builder()
                 .user(user)
                 .build();
                 
        cartRepository.save(cart);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
        );
        var user=userRepository.findByEmail(request.getEmail())
        .orElseThrow();
        // var cart=cartRepository.findByuser(user)
        // .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
