package com.greenfox.hackathon.service;

import com.greenfox.hackathon.exception.InvalidUsernameException;
import com.greenfox.hackathon.exception.MissingParameterException;
import com.greenfox.hackathon.exception.UserDoesNotExistException;
import com.greenfox.hackathon.exception.UsernameAlreadyTakenException;
import com.greenfox.hackathon.model.RegisterRequestDTO;
import com.greenfox.hackathon.model.User;
import com.greenfox.hackathon.model.Wishlist;
import com.greenfox.hackathon.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService( UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) throws UserDoesNotExistException {
        return userRepository.findUserByUsername(username).orElseThrow(UserDoesNotExistException::new);
    }

    public User addUser(User user) throws InvalidUsernameException {
        if (user.getUsername() == null || user.getUsername().length() == 0) {
      throw new InvalidUsernameException("Username must be a valid string.");
    }

    if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new InvalidUsernameException("Username is already taken.");
        }
       return userRepository.save(user);
    }

    public User register(RegisterRequestDTO registerRequestDTO)
            throws MissingParameterException, UsernameAlreadyTakenException {
        if (registerRequestDTO == null) {
            throw new MissingParameterException("Missing parameter(s): password, username, kingdomName!");
        }
        registerValidate(registerRequestDTO.getUsername(), registerRequestDTO.getPassword());
        Optional<User> findById = userRepository.findUserByUsername(registerRequestDTO.getUsername());
        if (findById.isPresent()) {
            throw new UsernameAlreadyTakenException();
        }
        return setup(registerRequestDTO);
    }

    private User setup(RegisterRequestDTO registerRequestDTO) {
        Wishlist wishlist = new Wishlist(formattedWishlistName(registerRequestDTO.getUsername()),null);
        User user = new User(registerRequestDTO.getUsername(), registerRequestDTO.getPassword(),wishlist);
        wishlist.setUser(user);
        saveUser(user);
        return userRepository.save(user);
    }

    private void registerValidate(String username, String password) throws MissingParameterException {
        List<String> missingFields = new ArrayList<>();
        if (username == null || username.equals("")) {
            missingFields.add("username");
        }
        if (password == null || password.equals("")) {
            missingFields.add("password");
        }
        if (!missingFields.isEmpty()) {
            String joined = String.join(", ", missingFields);
            throw new MissingParameterException("Missing parameter(s): " + joined + "!");
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findOptionalByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public String formattedWishlistName(String username) {
        return username + "'s wishlist";
    }
  public User createUser(RegisterRequestDTO request)
      throws InvalidUsernameException {
    User user = new User(request.getUsername(),
        request.getPassword(),
        UUID.randomUUID().toString(),
        request.getEmail(),
        false);
    return addUser(user);
  }
}
