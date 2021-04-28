package com.greenfox.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
  private Long id;
  private String name;
  private String department;
  private String description;
  private String linkToImage;
  private Long cost;
  private Long quantity;
  private Long discountedPrice;
  private Long discountRate;
}
