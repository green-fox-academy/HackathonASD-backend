package com.greenfox.hackathon.model;

public class OrderErrorResponseDTO {
  private String message;
  private String status = "error";

  public OrderErrorResponseDTO(String message) {
    this.message = message;
  }
}
