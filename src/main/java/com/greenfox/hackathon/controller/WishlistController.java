package com.greenfox.hackathon.controller;

import com.greenfox.hackathon.exception.NoSuchItemException;
import com.greenfox.hackathon.exception.UserDoesNotExistException;
import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.model.WishlistDTO;
import com.greenfox.hackathon.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class WishlistController {
  private WishlistService wishlistService;

  @Autowired
  public WishlistController(WishlistService wishlistService) {
    this.wishlistService = wishlistService;
  }

  @GetMapping("/wishlist")
  public ResponseEntity<WishlistDTO> getWishlistByUser(Principal principal) throws UserDoesNotExistException {
    return ResponseEntity.ok(wishlistService.getWishlist(principal));
  }

  @PutMapping("/wishlist")
  public ResponseEntity<Void> addItem(Principal principal, @RequestBody Long itemId)
      throws UserDoesNotExistException, NoSuchItemException {
    wishlistService.addItem(principal, itemId);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/wishlist/item/{id}")
  public ResponseEntity<Void> deleteItem(Principal principal, @PathVariable Long id)
      throws UserDoesNotExistException, NoSuchItemException {
    wishlistService.deleteItemById(id, principal);
    return ResponseEntity.noContent().build();
  }
}
