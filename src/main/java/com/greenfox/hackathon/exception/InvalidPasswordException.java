package com.greenfox.hackathon.exception;

public class InvalidPasswordException extends Exception {
  public String getErrorMessage() {
    return "Wrong password!";
  }
}
