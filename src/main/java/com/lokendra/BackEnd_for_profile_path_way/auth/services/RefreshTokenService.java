package com.lokendra.BackEnd_for_profile_path_way.auth.services;

import com.lokendra.BackEnd_for_profile_path_way.auth.entities.RefreshToken;
import com.lokendra.BackEnd_for_profile_path_way.auth.repositories.RefreshTokenRepo;
import com.lokendra.BackEnd_for_profile_path_way.entities.User;
import com.lokendra.BackEnd_for_profile_path_way.repositories.UserRepo;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepo refreshTokenRepo;
    private final UserRepo userRepo;

    public RefreshTokenService(RefreshTokenRepo refreshTokenRepo, UserRepo userRepo) {
        this.refreshTokenRepo = refreshTokenRepo;
        this.userRepo = userRepo;
    }

    public RefreshToken createRefreshToken(String username) {
        User user = this.userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with this username"));
        RefreshToken refreshToken = user.getRefreshToken();
        if (refreshToken == null) {
            long refreshTokenValidity = 60 * 60 * 1000;
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshTokenValidity))
                    .user(user)
                    .build();
            refreshTokenRepo.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refToken = refreshTokenRepo.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));
        if (refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(refToken);
            throw new RuntimeException("Refreshed token expired");
        }

        return refToken;
    }
}
