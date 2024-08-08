package com.lokendra.BackEnd_for_profile_path_way.auth.entities;

import com.lokendra.BackEnd_for_profile_path_way.entities.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "token_id")
    private Integer id;
    @Column(nullable = false, length = 500)
    @NotBlank(message = "Please enter refreshToken value")
    @JoinColumn(name = "refresh_token")
    private String refreshToken;
    @Column(nullable = false)
    @JoinColumn(name = "expiration_time")
    private Instant expirationTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
