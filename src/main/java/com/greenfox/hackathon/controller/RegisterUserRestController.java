package com.greenfox.hackathon.controller;

import com.greenfox.hackathon.exception.InvalidUsernameException;
import com.greenfox.hackathon.exception.MissingFieldException;
import com.greenfox.hackathon.exception.UnknownTokenException;
import com.greenfox.hackathon.model.RegisterRequestDTO;
import com.greenfox.hackathon.security.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterUserRestController {

  private final RegisterUserService registerUserService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public RegisterUserRestController(
      RegisterUserService registerUserService, PasswordEncoder passwordEncoder) {
    this.registerUserService = registerUserService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/register")
  public ResponseEntity<Object> registerResponse(
      @RequestBody(required = false) RegisterRequestDTO registerRequestDTO)
      throws MissingFieldException, InvalidUsernameException {
    if (registerRequestDTO == null) {
      registerRequestDTO = new RegisterRequestDTO();
    }

    registerRequestDTO.validate();
    registerRequestDTO.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
    return new ResponseEntity<>(registerUserService.registerUser(registerRequestDTO),
        HttpStatus.OK);
  }

  @GetMapping("/verify/{uuid}")
  public ResponseEntity<Object> validateUser(@PathVariable String uuid)
      throws UnknownTokenException {
    registerUserService.validateToken(uuid);
    return ResponseEntity.ok("<html>\n" + "<header><title>Welcome</title></header>\n" +
        "<body>\n" + "You are finally arrived\n" +
        "<script>setTimeout(function () {window.location.replace(\"/http://localhost:3000\"); }, 3000);</script>" +
        "</body>\n" + "</html>");
  }
}

