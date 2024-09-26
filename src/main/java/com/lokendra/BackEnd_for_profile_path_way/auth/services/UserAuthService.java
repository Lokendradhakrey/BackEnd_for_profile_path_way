package com.lokendra.BackEnd_for_profile_path_way.auth.services;


import com.lokendra.BackEnd_for_profile_path_way.auth.entities.UserRole;
import com.lokendra.BackEnd_for_profile_path_way.auth.utils.AuthResponse;
import com.lokendra.BackEnd_for_profile_path_way.auth.utils.LogInRequest;
import com.lokendra.BackEnd_for_profile_path_way.auth.utils.RegisterRequest;
import com.lokendra.BackEnd_for_profile_path_way.entities.User;
import com.lokendra.BackEnd_for_profile_path_way.repositories.UserRepo;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtUtilService jwtUtilService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    public UserAuthService(PasswordEncoder passwordEncoder, UserRepo userRepo, JwtUtilService jwtUtilService, RefreshTokenService refreshTokenService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.jwtUtilService = jwtUtilService;
        this.refreshTokenService = refreshTokenService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .fullName(registerRequest.getFullName())
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.USER)
                .build();
        User savedUser = userRepo.save(user);
        var accessToken = jwtUtilService.generateToken(savedUser);
        var refreshToken = refreshTokenService.createRefreshToken(savedUser.getUsername());
        var fullName = savedUser.getFullName();
        var userId = savedUser.getId();
        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .fullName(fullName)
                .userId(userId)
                .build();
    }

    public AuthResponse login(LogInRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepo.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found "));
        var accessToken = jwtUtilService.generateToken(user);
        var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());
        var fullName = user.getFullName();
        var userId = user.getId();

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .fullName(fullName)
                .userId(userId)
                .build();
    }
}
