package com.greenfox.hackathon.exception;

public class UserNotEnabledException extends Exception {
  public UserNotEnabledException() {
    super("Please verify your account.");
  }
}

