package com.greenfox.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginErrorResponseDTO extends LoginResponseDTO {
  private String message;

  public LoginErrorResponseDTO(String status, String message) {
    super(status);
    this.message = message;
  }
}
