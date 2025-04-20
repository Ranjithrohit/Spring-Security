package com.ranjith.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ranjith.entity.UserEntity;
import com.ranjith.repository.UserRepository;
import com.ranjith.request.LoginRequest;
import com.ranjith.request.SignupRequest;
import com.ranjith.response.LoginResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Override
    public String register(SignupRequest request) {
        UserEntity user = UserEntity.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .username(request.username())
                .build();
        return userRepository.save(user).getId().toString();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // Authenticate using email and password
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.email(), request.password());
        authenticationManager.authenticate(authentication);

        Optional<UserEntity> user = userRepository.findByEmail(request.email());
        if (user.isPresent()) {
            String token = jwtUtils.generateToken(user.get());
            return LoginResponse.builder()
                    .token(token)
                    .expiresIn(jwtUtils.getExpirationTime())
                    .build();
        }
        return LoginResponse.builder().build();
    }
}
