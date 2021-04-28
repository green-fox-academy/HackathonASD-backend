package com.greenfox.hackathon.security;

import com.greenfox.hackathon.exception.InvalidUserPasswordException;
import com.greenfox.hackathon.exception.InvalidUsernameException;
import com.greenfox.hackathon.exception.UnknownTokenException;
import com.greenfox.hackathon.model.RegisterRequestDTO;
import com.greenfox.hackathon.model.RegisterResponseDTO;
import com.greenfox.hackathon.model.User;
import com.greenfox.hackathon.repository.UserRepository;
import com.greenfox.hackathon.service.MailService;
import com.greenfox.hackathon.service.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterUserService {

  private final UserService userService;
  private final MailService mailService;
  private final UserRepository userRepository;

  public RegisterResponseDTO registerUser(RegisterRequestDTO request) throws
      InvalidUsernameException {
    User user = userService.createUser(request);
    String verifyUrl = System.getenv("VERIFY_URL");
    String url = verifyUrl + "/verify/" + user.getToken();
    mailService.sendActivationMail(request.getEmail(), url);
    return new RegisterResponseDTO(request.getUsername());
  }

  public void validateToken(String token) throws UnknownTokenException {
    Optional<User> userByToken = userRepository.findUserByToken(token);
    if (!userByToken.isPresent()) {
      throw new UnknownTokenException();
    }
    userByToken.get().setVerified(true);
    userRepository.save(userByToken.get());
  }

}

