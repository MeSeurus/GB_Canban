package com.planner.web.core.repositories;

import com.planner.web.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     Optional<User> findUserByNickname(String nickname);
     List<User> findAll();
     void deleteByNickname(String nickname);

}
