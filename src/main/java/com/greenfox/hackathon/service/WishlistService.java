package com.greenfox.hackathon.service;

import com.greenfox.hackathon.exception.NoSuchItemException;
import com.greenfox.hackathon.exception.UserDoesNotExistException;
import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.model.OrderDTO;
import com.greenfox.hackathon.model.User;
import com.greenfox.hackathon.model.Wishlist;
import com.greenfox.hackathon.model.WishlistDTO;
import com.greenfox.hackathon.repository.ItemRepository;
import com.greenfox.hackathon.repository.UserRepository;
import com.greenfox.hackathon.repository.WishlistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class WishlistService {
  private ItemRepository itemRepository;
  private UserRepository userRepository;
  private WishlistRepository wishlistRepository;

  @Autowired
  public WishlistService(WishlistRepository wishlistRepository, ItemRepository itemRepository,
                         UserRepository userRepository) {
    this.wishlistRepository = wishlistRepository;
    this.itemRepository = itemRepository;
    this.userRepository = userRepository;
  }

  public WishlistDTO getWishlist(Principal principal) throws UserDoesNotExistException {
    User user = userRepository.findUserByUsername(principal.getName()).orElseThrow(UserDoesNotExistException::new);

    Wishlist wishlist = user.getWishlist();
    return new WishlistDTO(wishlist.getItemList());

  }

  public void addItem(Principal principal, Long itemId) throws UserDoesNotExistException, NoSuchItemException {
    User user = userRepository.findUserByUsername(principal.getName()).orElseThrow(UserDoesNotExistException::new);
    Wishlist wishlist = user.getWishlist();
    Item item = itemRepository.findById(itemId).orElseThrow(NoSuchItemException::new);
    List<Item> itemList = wishlist.getItemList();
    itemList.add(item);
    wishlistRepository.save(wishlist);
  }

  public void deleteItemById(Long id, Principal principal) throws UserDoesNotExistException, NoSuchItemException {
    User user = userRepository.findUserByUsername(principal.getName()).orElseThrow(UserDoesNotExistException::new);
    Item item = itemRepository.findById(id).orElseThrow(NoSuchItemException::new);
    Wishlist wishlist = user.getWishlist();
    List<Item> itemList = wishlist.getItemList();

    IntStream.range(0, itemList.size()).filter(i -> itemList.get(i).equals(item))
        .forEach(i -> itemList.remove(itemList.get(i)));
  }
}
