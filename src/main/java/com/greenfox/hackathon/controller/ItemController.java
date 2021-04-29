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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
