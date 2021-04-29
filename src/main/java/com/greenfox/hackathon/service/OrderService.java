package com.greenfox.hackathon.service;


import com.greenfox.hackathon.exception.ItemOutOfStockException;
import com.greenfox.hackathon.exception.NoSuchItemException;
import com.greenfox.hackathon.exception.NoSuchOrderException;
import com.greenfox.hackathon.exception.UserDoesNotExistException;
import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.model.ItemMinDTO;
import com.greenfox.hackathon.model.ItemRequestDTO;
import com.greenfox.hackathon.model.Order;
import com.greenfox.hackathon.model.OrderDTO;
import com.greenfox.hackathon.model.User;
import com.greenfox.hackathon.repository.ItemRepository;
import com.greenfox.hackathon.repository.OrderRepository;
import com.greenfox.hackathon.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
  private OrderRepository orderRepository;
  private UserRepository userRepository;
  private ItemRepository itemRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository, UserRepository userRepository, ItemRepository itemRepository) {
    this.orderRepository = orderRepository;
    this.userRepository = userRepository;
    this.itemRepository = itemRepository;
  }

  public OrderDTO getOrderById(Long id) throws NoSuchOrderException {
    Order order = orderRepository.findById(id).orElseThrow(NoSuchOrderException::new);
    ModelMapper modelMapper = new ModelMapper();
    List<ItemMinDTO> collection = order.getItemList()
        .stream()
        .map(i -> modelMapper.map(i, ItemMinDTO.class))
        .collect(Collectors.toList());

    return new OrderDTO(order.getUser().getUsername(), collection);
  }

  public List<OrderDTO> getAllOrdersByUser(Principal principal) throws UserDoesNotExistException {
    User user = userRepository.findUserByUsername(principal.getName()).orElseThrow(UserDoesNotExistException::new);

    List<Order> orderList = user.getOrderList();

    List<Item> itemList = new ArrayList<>();

    for (Order order : orderList) {
      itemList = order.getItemList();
    }

    List<ItemMinDTO> itemMinDTOS = itemList.stream()
        .map(i -> new ItemMinDTO(i.getName(), i.getDepartment(), i.getCost()))
        .collect(Collectors.toList());


    return user.getOrderList()
        .stream()
        .map(o -> new OrderDTO(o.getUser().getUsername(), itemMinDTOS))
        .collect(Collectors.toList());
  }

  public OrderDTO addItemForExistingOrder(Long id, ItemRequestDTO itemRequestDTO) throws NoSuchOrderException {
    Order order = orderRepository.findById(id).orElseThrow(NoSuchOrderException::new);
    Item item = new Item(itemRequestDTO.getId());

    List<Item> items = order.getItemList();
    items.add(item);
    orderRepository.save(order);

    List<ItemMinDTO> collect = order.getItemList()
        .stream()
        .map(i -> new ItemMinDTO(i.getName(), i.getDepartment(), i.getCost()))
        .collect(Collectors.toList());

    return new OrderDTO(order.getUser().getUsername(), collect);
  }

  public void deleteOrderById(Long id) throws NoSuchOrderException {
    if (orderRepository.findById(id).isPresent()) {
      orderRepository.deleteById(id);
    } else {
      throw new NoSuchOrderException();
    }
  }

  public void deleteItemFromOrder(Long id, String itemName) throws NoSuchOrderException, NoSuchItemException {
    Order order = orderRepository.findById(id).orElseThrow(NoSuchOrderException::new);
    Item item = itemRepository.findItemByName(itemName).orElseThrow(NoSuchItemException::new);
    List<Item> itemList = order.getItemList();

    for (int i = 0; i < itemList.size(); i++) {
      if (itemList.get(i).equals(item)) {
        itemList.remove(itemList.get(i));
      }
    }
  }


  public void buyTheOrder(OrderDTO orderDTO) throws ItemOutOfStockException {
    List<ItemMinDTO> itemList = orderDTO.getItems();
    for (ItemMinDTO item : itemList) {
      if (item.getQuantity() == 0) {
        throw new ItemOutOfStockException("Item out of stock: " + item.getName());
      } else {
        Item itemInRepo = itemRepository.findItemByName(item.getName()).get();
        itemInRepo.setQuantity(itemInRepo.getQuantity() - 1);
        itemRepository.save(itemInRepo);
      }
    }
  }
}
