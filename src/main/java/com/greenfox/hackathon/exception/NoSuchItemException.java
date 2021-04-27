package com.greenfox.hackathon.exception;

public class NoSuchItemException extends Exception{
  public String message() {
    return "No such item";
  }
}

