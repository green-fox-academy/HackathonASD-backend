package com.greenfox.hackathon.model;

import com.greenfox.hackathon.exception.MissingFieldException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

  String username;
  String email;
  String password;

  public void validate() throws MissingFieldException {
    List<String> fields = new ArrayList<>();
    if (username == null || username.length() == 0) {
      fields.add("username");
    }
    if (email == null || email.length() == 0) {
      fields.add("email");
    }
    if (password == null || password.length() == 0) {
      fields.add("password");
    }
    if (!fields.isEmpty()) {
      throw new MissingFieldException(fields);
    }
  }

}

