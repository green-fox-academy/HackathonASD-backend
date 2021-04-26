package com.greenfox.hackathon.exception;

public class UsernameAlreadyTakenException extends Exception {
  @Override
  public String getMessage() {
    return "Username already taken, please choose an other one.";
  }
}
