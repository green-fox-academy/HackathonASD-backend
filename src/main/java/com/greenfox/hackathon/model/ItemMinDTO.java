package com.greenfox.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemMinDTO {
  private String name;
  private String department;
  private Long cost;
  private Long quantity;

  public ItemMinDTO(String name, String department, Long cost){
    this.name = name;
    this.department = department;
    this.cost = cost;
  }
}
