package com.greenfox.hackathon.security;

import com.greenfox.hackathon.model.User;
import com.greenfox.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public AppUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findUserByUsername(username);
    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
    return user.map(AppUserDetails::new).get();

  }
}
