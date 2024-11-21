package com.example.autoAlert360Pro.auth;

import com.example.autoAlert360Pro.config.JwtService;
import com.example.autoAlert360Pro.entities.User;
import com.example.autoAlert360Pro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {

        var user  = User.builder()
                .username(request.getName())
                .email(request.getEmail())
                .enabled(true)
                .password(passwordEncoder.encode(request.getPassword())).build();
                 repository.save(user);
        String jwtToken  = jwtService.generateToken( user);
        return  AuthenticationResponse.builder().token(jwtToken).build();
    }
   public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
       if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
           throw new BadCredentialsException("Credenciales inválidas");
       }
        var jwtToken  = jwtService.generateToken(user);
        return  AuthenticationResponse.builder().token(jwtToken).build();
    }
}
