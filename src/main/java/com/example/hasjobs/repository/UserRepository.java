package com.example.hasjobs.repository;

import com.example.hasjobs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    User findByName(String name);
}
