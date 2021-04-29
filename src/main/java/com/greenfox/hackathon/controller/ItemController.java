package com.greenfox.hackathon.controller;

import com.greenfox.hackathon.exception.InvalidItemDTOException;
import com.greenfox.hackathon.exception.InvalidItemUpdateDTOException;
import com.greenfox.hackathon.exception.MissingParameterException;
import com.greenfox.hackathon.exception.NoSuchItemException;
import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.model.ItemDTO;
import com.greenfox.hackathon.model.ItemUpdateDTO;
import com.greenfox.hackathon.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ItemController {

  ItemService itemService;

  @Autowired
  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  @PostMapping("/item")
  public ResponseEntity<Item> saveItemToDatabase(@RequestBody(required = false) ItemDTO itemDTO)
      throws InvalidItemDTOException {
    return ResponseEntity.ok(itemService.saveItem(itemDTO));
  }

  @GetMapping("/item")
  public ResponseEntity<?> getAllItems(
      @RequestParam(required = false, defaultValue = "all") String filter, Pageable pageable) {
    return itemService.getFilteredItems(filter, pageable);
  }

  @GetMapping("/item/{id}")
  public ResponseEntity<?> getItem(@PathVariable long id) throws NoSuchItemException {
    return ResponseEntity.ok(itemService.findItem(id));
  }

  @PutMapping("/item")
  public ResponseEntity<Item> updateItem(@RequestBody(required = false)
                                             ItemUpdateDTO itemUpdateDTO)
      throws InvalidItemUpdateDTOException, NoSuchItemException {
    return ResponseEntity.ok(itemService.updateItem(itemUpdateDTO));
  }

  @DeleteMapping("/item/{id}")
  public ResponseEntity<Item> deleteItem(@PathVariable
                                             Long id) throws MissingParameterException {
    return ResponseEntity.ok(itemService.deleteItem(id));
  }

  //todo Integration tests

//  @GetMapping("/getAllItems/sorted")
//  public ResponseEntity<?> getSortedItemsByPrice() {
//    return ResponseEntity.ok(itemService.getSortedItemsByPrice());
//  }
//
//  @GetMapping("/getAllItems/sorted/desc")
//  public ResponseEntity<?> getSortedItemsByPriceDesc() {
//    return ResponseEntity.ok(itemService.getSortedItemsByPriceDesc());
//  }
//
//  @GetMapping("/getItemsUnderCertainPrice")
//  public ResponseEntity<?> getItemsUnderCertainPrice(@RequestParam Long price) {
//    return ResponseEntity.ok(itemService.getItemsUnderCertainPrice(price));
//  }
//
//  @GetMapping("/getSearchedItems")
//  public ResponseEntity<?> getSearchedItems(@RequestParam String searchedItem) {
//    return ResponseEntity.ok(itemService.getSearchedItems(searchedItem));
//  }
}
