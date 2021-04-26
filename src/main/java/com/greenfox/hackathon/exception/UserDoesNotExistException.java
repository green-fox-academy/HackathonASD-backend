package com.greenfox.hackathon.exception;

public class UserDoesNotExistException extends Exception {
  public String message() {
    return "username does not exist";
  }
}
