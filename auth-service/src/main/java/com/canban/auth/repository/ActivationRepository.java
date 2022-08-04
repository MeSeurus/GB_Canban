package com.canban.auth.repository;

import com.canban.auth.entity.security.CodeType;
import com.canban.auth.entity.security.UserAwaitActivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivationRepository extends JpaRepository<UserAwaitActivation, String> {
    Optional<UserAwaitActivation> findByUsername(String username);

    @Query("SELECT COUNT (u) FROM UserAwaitActivation u WHERE u.username = :username AND u.secretCode = :passcode AND u.codeType = :passwordRemindCode")
    int findExistingUserAndCode(String username, String passcode, CodeType passwordRemindCode);
}
