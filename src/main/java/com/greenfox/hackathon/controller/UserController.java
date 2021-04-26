package com.greenfox.hackathon.controller;

import com.greenfox.hackathon.exception.InvalidPasswordException;
import com.greenfox.hackathon.exception.MissingParameterException;
import com.greenfox.hackathon.exception.UsernameAlreadyTakenException;
import com.greenfox.hackathon.model.LoginRequestDTO;
import com.greenfox.hackathon.model.LoginSuccessResponseDTO;
import com.greenfox.hackathon.model.RegisterRequestDTO;
import com.greenfox.hackathon.model.RegisterSuccessResponseDTO;
import com.greenfox.hackathon.model.User;
import com.greenfox.hackathon.security.JwtUtil;
import com.greenfox.hackathon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
  private UserService userService;
  private JwtUtil jwtUtil;

  @Autowired
  public UserController(UserService userService, JwtUtil jwtUtil) {
    this.userService = userService;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<Object> registration(@RequestBody(required = false) RegisterRequestDTO registerRequestDTO)
      throws UsernameAlreadyTakenException, MissingParameterException {
    User saved = userService.register(registerRequestDTO);
    return ResponseEntity.ok(new RegisterSuccessResponseDTO(saved.getUsername()));
  }

  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<Object> login(@RequestBody(required = false) LoginRequestDTO loginRequest)
      throws MissingParameterException, InvalidPasswordException {
    UserDetails userDetails = userService.authenticateUser(loginRequest);
    String token = jwtUtil.generateToken(userDetails.getUsername());
    return ResponseEntity.ok(new LoginSuccessResponseDTO("ok", token));
  }
}
