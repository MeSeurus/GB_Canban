package com.canban.auth.repository;

import com.canban.auth.entity.security.UserAwaitActivation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationRepository  extends JpaRepository<UserAwaitActivation, String> {
    Optional<UserAwaitActivation> findByUsername(String username);
}
