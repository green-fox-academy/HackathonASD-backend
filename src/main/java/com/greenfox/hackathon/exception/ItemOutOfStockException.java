package com.greenfox.hackathon.exception;

public class ItemOutOfStockException extends Exception {
  public ItemOutOfStockException(String message) {
    super(message);
  }
}
