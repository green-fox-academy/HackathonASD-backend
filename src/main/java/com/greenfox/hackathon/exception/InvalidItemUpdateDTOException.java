package com.greenfox.hackathon.exception;

public class InvalidItemUpdateDTOException extends Exception{
  public String getErrorMessage() {
    return "Invalid fields in itemUpdateDTO";
  }

}
