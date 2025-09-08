package com.repository;

import com.model.OneTimeToken;
import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

public interface OneTimeTokenRepository extends JpaRepository<OneTimeToken, Long> {
    // Optional<OneTimeToken> findByTokenAndUsedFalse(String token);
    // void deleteByExpiresAtBefore(java.time.LocalDateTime now);
}
