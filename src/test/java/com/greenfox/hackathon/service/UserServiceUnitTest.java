package com.greenfox.hackathon.service;

import com.greenfox.hackathon.exception.MissingParameterException;
import com.greenfox.hackathon.exception.UserDoesNotExistException;
import com.greenfox.hackathon.exception.UsernameAlreadyTakenException;
import com.greenfox.hackathon.model.RegisterRequestDTO;
import com.greenfox.hackathon.model.User;
import com.greenfox.hackathon.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceUnitTest {
  @Mock
  UserRepository mockedUserRepository;
  UserService userService;
  User user;
  UserDetailsService userDetailsService;
  PasswordEncoder passwordEncoder;

//  @Before
//  public void setup() {
//    mockedUserRepository = Mockito.mock(UserRepository.class);
//
//    userService = new UserService(userDetailsService, mockedUserRepository);
//    user = new User("user", "pass");
//  }

  @Test
  public void givenGetUserByUsername_whenRepoReturnsNotEmptyOptional_thenReturnsValidUser() throws Exception {
    String expectedUsername = "user";
    when(mockedUserRepository.findUserByUsername("user")).thenReturn(Optional.of(user));
    User actualUser = userService.getUserByUsername("user");
    assertEquals(expectedUsername, actualUser.getUsername());
  }

  @Test(expected = UserDoesNotExistException.class)
  public void givenGetUserByUsername_whenRepoReturnsEmptyOptional_thenThrowsException() throws Exception {
    when(mockedUserRepository.findUserByUsername("user")).thenReturn(Optional.of(user));
    User actualUser = userService.getUserByUsername("asd");
  }


  @Test(expected = MissingParameterException.class)
  public void givenRegister_whenUsernameIsNull_thenThrowsMissingFieldException()
      throws MissingParameterException, UsernameAlreadyTakenException {
    userService.register(new RegisterRequestDTO(null, "pass", "pass"));
  }

  @Test(expected = MissingParameterException.class)
  public void givenRegister_whenPasswordIsNull_thenThrowsMissingFieldException()
      throws MissingParameterException, UsernameAlreadyTakenException {
    userService.register(new RegisterRequestDTO("user", "pass", null));
  }

  @Test(expected = MissingParameterException.class)
  public void givenRegister_whenRequestIsNull_thenThrowsMissingFieldException()
      throws MissingParameterException, UsernameAlreadyTakenException {
    userService.register(new RegisterRequestDTO(null, null, null));
  }


  @Test(expected = MissingParameterException.class)
  public void givenRegister_whenUsernameIsEmpty_thenThrowsMissingFieldException()
      throws MissingParameterException, UsernameAlreadyTakenException {
    userService.register(new RegisterRequestDTO("","pass", "password"));
  }

  @Test(expected = MissingParameterException.class)
  public void givenRegister_whenPasswordIsEmpty_thenThrowsMissingFieldException()
      throws MissingParameterException, UsernameAlreadyTakenException {
    userService.register(new RegisterRequestDTO("user", "pass", ""));
  }
}

