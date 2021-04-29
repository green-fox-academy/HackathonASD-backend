package com.greenfox.hackathon.controller;

import com.greenfox.hackathon.exception.InvalidPasswordException;
import com.greenfox.hackathon.exception.MissingParameterException;
import com.greenfox.hackathon.model.LoginRequestDTO;
import com.greenfox.hackathon.model.LoginSuccessResponseDTO;
import com.greenfox.hackathon.security.JwtUtil;
import com.greenfox.hackathon.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
  private final LoginService loginService;
  private final JwtUtil jwtUtil;

  @Autowired
  public UserController(JwtUtil jwtUtil,
                        LoginService loginService) {
    this.jwtUtil = jwtUtil;
    this.loginService = loginService;
  }

  @PostMapping("/login")
  @ResponseBody
  public ResponseEntity<Object> login(@RequestBody(required = false) LoginRequestDTO loginRequest)
      throws MissingParameterException, InvalidPasswordException {
    UserDetails userDetails = loginService.authenticateUser(loginRequest);
    String token = jwtUtil.generateToken(userDetails.getUsername());
    return ResponseEntity.ok(new LoginSuccessResponseDTO("ok", token));
  }
}
