package com.greenfox.hackathon.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseSuccessDTO {
  private String message = "Thank you for ordering! We have sent you an email with the details of your order. ";
}
