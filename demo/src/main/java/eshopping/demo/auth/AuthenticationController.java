package eshopping.demo.auth;

import org.springframework.web.bind.annotation.RestController;

import eshopping.demo.auth.AuthenticationResponse;
import eshopping.demo.auth.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor

public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity register(
        @RequestBody @Valid RegisterRequest request, BindingResult bindingResult
        ) throws Exception {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
            return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity register(
        @RequestBody @Valid AuthenticationRequest request, BindingResult bindingResult
        ) throws Exception {
            if (bindingResult.hasErrors()) {
                return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
            }
            return ResponseEntity.ok(service.authenticate(request));   
         }
    

}
