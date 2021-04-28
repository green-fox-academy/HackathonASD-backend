package com.greenfox.hackathon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Item(String name) {
        this.name = name;
    }
}
