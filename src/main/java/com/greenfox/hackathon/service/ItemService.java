package com.greenfox.hackathon.service;

import com.greenfox.hackathon.exception.InvalidItemDTOException;
import com.greenfox.hackathon.exception.InvalidItemUpdateDTOException;
import com.greenfox.hackathon.exception.MissingParameterException;
import com.greenfox.hackathon.exception.NoSuchItemException;
import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.model.ItemDTO;
import com.greenfox.hackathon.model.ItemUpdateDTO;
import com.greenfox.hackathon.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

  ItemRepository itemRepository;
  ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public ItemService(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  public List<Item> getAllItems() {
    return itemRepository.findAll();
  }

  public Item saveItem(ItemDTO itemDTO) throws InvalidItemDTOException {
    validateItemDTO(itemDTO);
    return itemRepository.save(modelMapper.map(itemDTO, Item.class));
  }

  public void validateItemDTO(ItemDTO itemDTO) throws InvalidItemDTOException {
    if (itemDTO == null) {
      throw new InvalidItemDTOException();
    }
    if (itemDTO.getCost() == null || itemDTO.getDepartment().equals("") ||
        itemDTO.getDescription().equals("") || itemDTO.getId() == null ||
        itemDTO.getLinkToImage().equals("") || itemDTO.getName().equals("")) {
      throw new InvalidItemDTOException();
    }
  }

  public Item updateItem(ItemUpdateDTO itemUpdateDTO)
      throws InvalidItemUpdateDTOException, NoSuchItemException {
    validateItemUpdateDTO(itemUpdateDTO);
    if (itemRepository.findById(itemUpdateDTO.getId()).isPresent()) {
      Item newItem = itemRepository.findById(itemUpdateDTO.getId()).get();
      setDataByItemUpdateDTO(itemUpdateDTO, newItem);
      return itemRepository.save(newItem);
    } else {
      throw new NoSuchItemException();
    }
  }

  public void validateItemUpdateDTO(ItemUpdateDTO itemUpdateDTO)
      throws InvalidItemUpdateDTOException {
    if (itemUpdateDTO == null) {
      throw new InvalidItemUpdateDTOException();
    }
    if (itemUpdateDTO.getNewDate().equals("") ||
        itemUpdateDTO.getItemPartToUpdate().equals("") || itemUpdateDTO.getId() == null) {
      throw new InvalidItemUpdateDTOException();
    }
  }

  public void setDataByItemUpdateDTO(ItemUpdateDTO itemUpdateDTO, Item newItem) {
    if (itemUpdateDTO.getItemPartToUpdate().equals("name")) {
      newItem.setName(itemUpdateDTO.getNewDate());
    }
    if (itemUpdateDTO.getItemPartToUpdate().equals("department")) {
      newItem.setName(itemUpdateDTO.getNewDate());
    }
    if (itemUpdateDTO.getItemPartToUpdate().equals("description")) {
      newItem.setName(itemUpdateDTO.getNewDate());
    }
    if (itemUpdateDTO.getItemPartToUpdate().equals("linkToImage")) {
      newItem.setName(itemUpdateDTO.getNewDate());
    }
    if (itemUpdateDTO.getItemPartToUpdate().equals("cost")) {
      newItem.setName(itemUpdateDTO.getNewDate());
    }
  }

  public Item deleteItem(Long id) throws MissingParameterException {
    if (id == null) {
      throw new MissingParameterException("Please provide an id!");
    } else {
      Item itemToDelete = itemRepository.findById(id).get();
      itemRepository.deleteById(id);
      return itemToDelete;
    }
  }

  //todo test for functions


//  public List<Item> getSortedItemsByPrice() {
//    return itemRepository.getSortedItemsByPrice();
//  }
//
//  public List<Item> getSortedItemsByPriceDesc() {
//    return itemRepository.getSortedItemsByPriceDesc();
//  }
//
//  public List<Item> getItemsUnderCertainPrice(Long price) {
//    return itemRepository.getItemsUnderCertainPrice(price);
//  }
//
//  public List<Item> getSearchedItems(String searchedItem) {
//    return itemRepository.getSearchedItems(searchedItem);
//  }


}
