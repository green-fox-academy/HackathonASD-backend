package com.greenfox.hackathon.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String department;
  private String description;
  private String linkToImage;
  private Long cost;
  private Long quantity;
  @Column(name = "discounted_price")
  private Long discountedPrice;
  private Long discountRate = 0L;
  @Column(name = "discounted")
  private boolean isDiscounted = false;

  @ManyToMany(mappedBy = "itemList")
  @JsonIgnore
  private List<Order> orderList = new ArrayList<>();

  @ManyToMany
  @JoinTable(
      name = "Wishlist_Item",
      joinColumns = {@JoinColumn(name = "wishlist_id")},
      inverseJoinColumns = {@JoinColumn(name = "item_id")}
  )
  private List<Wishlist> itemWishlist = new ArrayList<>();

  public Item(String name) {
    this.name = name;
  }

  public Item(Long id) {
    this.id = id;
  }
}
