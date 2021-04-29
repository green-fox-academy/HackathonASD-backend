package com.greenfox.hackathon.service;

import com.greenfox.hackathon.exception.InvalidPasswordException;
import com.greenfox.hackathon.exception.MissingParameterException;
import com.greenfox.hackathon.model.LoginRequestDTO;
import com.greenfox.hackathon.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final UserDetailsService userDetailsService;


  public LoginService(PasswordEncoder passwordEncoder,
                      UserRepository userRepository,
                      UserDetailsService userDetailsService) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.userDetailsService = userDetailsService;
  }

  public UserDetails authenticateUser(LoginRequestDTO loginRequest)
      throws InvalidPasswordException,
      MissingParameterException {
    readLoginRequest(loginRequest);
    UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
    checkPassword(loginRequest, userDetails);
    return userDetails;
  }

  private void checkPassword(LoginRequestDTO loginRequest, UserDetails userDetails) throws
      InvalidPasswordException {
    validateUser(loginRequest.getUsername(), loginRequest.getPassword());
  }

  public void validateUser(String username, String password)
      throws InvalidPasswordException {
    if (!passwordEncoder
        .matches(password, userRepository.findUserByUsername(username).get().getPassword())) {
      throw new InvalidPasswordException();
    }
  }

  public void readLoginRequest(LoginRequestDTO loginRequest)
      throws MissingParameterException {
    if (loginRequest == null) {
      throw new MissingParameterException("Missing parameter(s): password, username!");
    }

    Map<String, String> loginDetails =
        loginValidate(loginRequest.getUsername(), loginRequest.getPassword());
    setupValidate(loginDetails);
  }

  public void setupValidate(Map<String, String> fields) throws MissingParameterException {
    List<String> missingFields = new ArrayList<>();
    for (Map.Entry<String, String> entry : fields.entrySet()) {
      if (entry.getValue() == null || entry.getValue().equals("")) {
        missingFields.add(entry.getKey());
      }
    }
    if (!missingFields.isEmpty()) {
      String joined = String.join(", ", missingFields);
      throw new MissingParameterException("Missing parameter(s): " + joined + "!");
    }
  }

  public Map<String, String> loginValidate(String username, String password) {
    Map<String, String> missingFields = new HashMap<>();
    missingFields.put("username", username);
    missingFields.put("password", password);
    return missingFields;
  }

}
