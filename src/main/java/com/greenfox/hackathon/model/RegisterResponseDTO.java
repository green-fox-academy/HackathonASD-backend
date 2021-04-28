package com.greenfox.hackathon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponseDTO {

  private String username;

  public RegisterResponseDTO(String username) {
    this.username = username;

  }

}
