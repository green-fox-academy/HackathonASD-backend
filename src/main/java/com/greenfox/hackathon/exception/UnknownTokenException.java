package com.greenfox.hackathon.exception;

public class UnknownTokenException extends Exception {
  public UnknownTokenException() {
    super("This token does not exist.");
  }
}
