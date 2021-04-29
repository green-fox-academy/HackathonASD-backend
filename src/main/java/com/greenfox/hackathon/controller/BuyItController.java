package com.greenfox.hackathon.controller;

import com.greenfox.hackathon.exception.ItemOutOfStockException;
import com.greenfox.hackathon.exception.UserDoesNotExistException;
import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.model.OrderDTO;
import com.greenfox.hackathon.model.PurchaseSuccessDTO;
import com.greenfox.hackathon.model.User;
import com.greenfox.hackathon.service.OrderService;
import com.greenfox.hackathon.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyItController {

  private final UserService userService;
  private final OrderService orderService;

  @Autowired
  public BuyItController(UserService userService, OrderService orderService) {
    this.userService = userService;
    this.orderService = orderService;
  }

  @PostMapping("/buy")
  public ResponseEntity<OrderDTO> purchaseTheOrder(@RequestBody OrderDTO orderDTO,
                                                   Principal principal)
      throws UserDoesNotExistException, ItemOutOfStockException {
    User user = userService.getUserByUsername(principal.getName());
    orderService.buyTheOrder(orderDTO);
    return new ResponseEntity<>(orderDTO, HttpStatus.OK);
  }
}
