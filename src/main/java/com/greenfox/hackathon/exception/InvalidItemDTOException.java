package com.greenfox.hackathon.exception;

public class InvalidItemDTOException extends Exception{
  public String getErrorMessage() {
    return "Invalid fields in itemDTO";
  }
}
