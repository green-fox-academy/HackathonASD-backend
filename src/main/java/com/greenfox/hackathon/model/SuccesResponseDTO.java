package com.greenfox.hackathon.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccesResponseDTO {

  private String status;
  private String message;

  public SuccesResponseDTO(String message) {
    this.status = "success";
    this.message = message;
  }

}

