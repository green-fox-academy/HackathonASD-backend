package com.greenfox.hackathon.model;

public class ItemErrorResponseDTO {
  private String message;
  private String status = "error";

  public ItemErrorResponseDTO(String message) {
    this.message = message;
  }
}
