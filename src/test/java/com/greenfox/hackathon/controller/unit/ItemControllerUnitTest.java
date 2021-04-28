package com.greenfox.hackathon.controller.unit;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.greenfox.hackathon.exception.InvalidItemDTOException;
import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.service.ItemService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ItemService itemService;

  @Test
  public void givenGetAllItems_whenItCalled_thenReturnListOfItems() throws Exception {
    List<Item> items = new ArrayList<>();
    items.add(new Item( "Watch"));
    items.add(new Item("Balloon"));
    Page<Item> page = new PageImpl<>(items);
    when(itemService.getAllItems()).thenReturn(items);
    mockMvc.perform(get("/item")
        .param("page", "6")
        .param("size", "14")
        .param("sort", "cost,asc")
        .param("sort", "name,desc")
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(2)))
        .andExpect(jsonPath("$.content[0].name", is("Watch")))
        .andExpect(jsonPath("$.content[1].name", is("Balloon")));
  }

  @Test
  public void givenGetAllItems_whenItCalled_thenReturnEmptyListOfItems() throws Exception {
    List<Item> items = new ArrayList<>();
    Page<Item> page = new PageImpl<>(items);
    when(itemService.getAllItems())
        .thenReturn(items);
    mockMvc.perform(get("/item"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(0)))
        .andExpect(jsonPath("$.content").exists());
  }

  @Test
  public void givenSaveItem_whenItCalled_thenReturnSavedItem() throws Exception {
    when(itemService.saveItem(any()))
        .thenReturn(new Item( "Watch"));
    mockMvc.perform(post("/item"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Watch")));
  }

  @Test
  public void givenUpdateItem_whenItCalled_thenReturnUpdatedItem() throws Exception {
    when(itemService.updateItem(any()))
        .thenReturn(new Item( "Watch"));
    mockMvc.perform(put("/item"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Watch")));
  }

  @Test
  public void givenDeleteItem_whenItCalled_thenReturnDeletedItem() throws Exception {
    when(itemService.deleteItem(any()))
        .thenReturn(new Item( "Watch"));
    mockMvc.perform(delete("/item"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", is("Watch")));
  }

}
