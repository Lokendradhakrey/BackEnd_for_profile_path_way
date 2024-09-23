package com.lokendra.BackEnd_for_profile_path_way.controllers;

import com.lokendra.BackEnd_for_profile_path_way.auth.entities.RefreshToken;
import com.lokendra.BackEnd_for_profile_path_way.auth.services.JwtUtilService;
import com.lokendra.BackEnd_for_profile_path_way.auth.services.RefreshTokenService;
import com.lokendra.BackEnd_for_profile_path_way.auth.services.UserAuthService;
import com.lokendra.BackEnd_for_profile_path_way.auth.utils.AuthResponse;
import com.lokendra.BackEnd_for_profile_path_way.auth.utils.LogInRequest;
import com.lokendra.BackEnd_for_profile_path_way.auth.utils.RefreshTokenRequest;
import com.lokendra.BackEnd_for_profile_path_way.auth.utils.RegisterRequest;
import com.lokendra.BackEnd_for_profile_path_way.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile-path-way/v1/auth")
public class AuthController {

    private final UserAuthService userAuthService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtilService jwtUtilService;

    public AuthController(UserAuthService userAuthService, RefreshTokenService refreshTokenService, JwtUtilService jwtUtilService) {
        this.userAuthService = userAuthService;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtilService = jwtUtilService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userAuthService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LogInRequest loginRequest) {
        return ResponseEntity.ok(userAuthService.login(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(request.getRefreshToken());
        User user = refreshToken.getUser();
        String accessToken = jwtUtilService.generateToken(user);
        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build());
    }
}
