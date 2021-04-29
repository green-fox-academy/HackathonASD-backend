package com.greenfox.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;
  @NotNull
  private String token;
  @NotNull
  private String email;
  private boolean verified;

  @OneToMany(mappedBy = "user")
  private List<Order> orderList = new ArrayList<>();

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "wishlist_id")
  private Wishlist wishlist;

  public User(String username, @NotNull String password,Wishlist wishlist) {
    this.username = username;
    this.password = password;
    this.email = "";
    this.token = "";
    this.wishlist = wishlist;
  }

  public User(String username, String password, String token, String email, boolean verified) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.token = token;
    this.verified = verified;
  }
}
