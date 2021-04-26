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

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepository.findUserByUsername(username);
    optionalUser.orElseThrow(() -> new UsernameNotFoundException("No such user: " + username + "!"));
    return optionalUser.map(AppUserDetails::new).get();
  }
}
