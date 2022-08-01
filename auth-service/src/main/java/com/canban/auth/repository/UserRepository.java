package com.canban.auth.repository;

import com.canban.auth.entity.User;
import com.canban.auth.entity.security.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    /**
    * Пришлось поставить тут @Transactional, почему-то несмотря на то, что в методах стоит эта аннотация - без нее выдает TransactionRequiredException
    */

    @Transactional
    @Modifying
    @Query("update User u set u.userStatus = ?2 where u.username = ?1")
    void updateStatus(String username,UserStatus status);

    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2 where u.username = ?1")
    void updatePassword(String username,String password);


    @Query("SELECT u.email FROM User u WHERE u.username = :username")
    Optional<String> getEmailByUsername(@Param("username") String username);

}
