package com.greenfox.hackathon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wishlists")
public class Wishlist {

    @Id
    @Column(name = "wishlist_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @ManyToMany(mappedBy = "itemWishlist")
    private List<Item> itemList;

    @OneToOne(mappedBy = "wishlist",cascade = CascadeType.ALL)
    private User user;

    private String wishlistName;

    public Wishlist(String wishlistName, User user) {
        this.wishlistName = wishlistName;
        this.user = user;
    }
}
