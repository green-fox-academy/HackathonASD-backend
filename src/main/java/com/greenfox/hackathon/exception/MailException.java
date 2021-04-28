package com.greenfox.hackathon.exception;

public class MailException extends RuntimeException {
  public MailException(String exMessage, Exception exception) {
    super(exMessage, exception);
  }

}

