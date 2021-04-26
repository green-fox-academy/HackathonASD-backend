package com.greenfox.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterErrorResponseDTO {
  private String status = "error";
  private String message;

  public RegisterErrorResponseDTO(String message) {
    this.message = message;
  }
}
