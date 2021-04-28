package com.greenfox.hackathon.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

  public User(String username, @NotNull String password) {
    this.username = username;
    this.password = password;
    this.email = "";
    this.token = "";
  }

  public User(String username, String password, String token, String email, boolean verified) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.token = token;
    this.verified = verified;
  }
}
