package com.greenfox.hackathon.repository;

import com.greenfox.hackathon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByUsername(String username);

  Optional<User> findUserByToken(String username);

  Optional<User> findUserByUsernameAndPassword(String username, String password);

  Optional<User> findUserByUsernameAndVerified(String username, Boolean verified);
}
