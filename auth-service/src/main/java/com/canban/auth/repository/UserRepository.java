package com.canban.auth.repository;

import com.canban.auth.entity.User;
import com.canban.auth.entity.security.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User u set u.userStatus = ?2 where u.username = ?1")
    void updateStatus(String username,UserStatus status);

}
