package com.example.hasjobs.service;

import com.example.hasjobs.entity.User;
import com.example.hasjobs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUseByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        }
        return null;
    }

    public User saveUser(String name, String email, String phone) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public boolean bothPasswordsSame(String password, String reEnteredPassword) {
        if (password.equals(reEnteredPassword)) {
            return true;
        }
        return false;
    }

    public void saveTheUser(User newUser) {
        userRepository.save(newUser);
    }

    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    public User findUserById(int userId) {
        User user = userRepository.findById(userId).get();
        return user;
    }
}
