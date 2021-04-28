package com.greenfox.hackathon.exception;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MissingFieldException extends Exception {

  @Override
  public String getMessage() {
    return "Missing parameter(s): " + String.join(",", fields) + "!";
  }

  List<String> fields;

}

