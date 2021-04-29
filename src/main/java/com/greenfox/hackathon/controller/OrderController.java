package com.greenfox.hackathon.controller;

import com.greenfox.hackathon.exception.NoSuchItemException;
import com.greenfox.hackathon.exception.NoSuchOrderException;
import com.greenfox.hackathon.exception.UserDoesNotExistException;
import com.greenfox.hackathon.model.ItemRequestDTO;
import com.greenfox.hackathon.model.OrderDTO;
import com.greenfox.hackathon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class OrderController {
  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/order/{id}")
  public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) throws NoSuchOrderException {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  @GetMapping("/order")
  public ResponseEntity<List<OrderDTO>> getAllOrderByUser(Principal principal) throws UserDoesNotExistException {
    return ResponseEntity.ok(orderService.getAllOrdersByUser(principal));
  }

  @PostMapping("/order/{id}")
  public ResponseEntity<OrderDTO> addItemForExistingOrder(@PathVariable Long id,
                                                          @RequestBody ItemRequestDTO itemRequestDTO)
      throws NoSuchOrderException {
    return ResponseEntity.ok(orderService.addItemForExistingOrder(id, itemRequestDTO));
  }

  @DeleteMapping("/order/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws NoSuchOrderException {
    orderService.deleteOrderById(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/order/item/{id}")
  public ResponseEntity<Void> deleteItem(@PathVariable Long id, @RequestParam String itemName)
      throws NoSuchItemException, NoSuchOrderException {
    orderService.deleteItemFromOrder(id, itemName);
    return ResponseEntity.noContent().build();

  }
}
