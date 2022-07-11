package com.planner.web.core.services;

import com.planner.web.core.entities.User;
import com.planner.web.core.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUserByUsername(String nickname) {
        return userRepository.findUserByNickname(nickname);
    }

    public void createNew(String nickname) {
        User user = new User(nickname);
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void deleteByNickname(String nickname) {
        userRepository.deleteByNickname(nickname);
    }

}
