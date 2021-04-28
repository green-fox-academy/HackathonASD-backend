package com.greenfox.hackathon.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.repository.ItemRepository;
import com.greenfox.hackathon.service.ItemService;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(properties = {"JWT_SECRET_KEY"})
public class ItemControllerIntegrationTest {

  @Autowired
  ItemService itemService;
  @Autowired
  ItemRepository itemRepository;
  @Autowired
  private MockMvc mockMvc;

  private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      StandardCharsets.UTF_8);

  @Test
  public void givenGetAllItems_whenCalled_thenReturnItemList()
      throws Exception {
    List<Item> items = itemRepository.findAll();
    int repoSize = items.size();
    mockMvc.perform(get("/item")
        .param("page", "0")
        .param("size", "10")
        .param("sort", "cost,asc")
        .param("sort", "name,desc"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content", hasSize(repoSize)))
        .andExpect(jsonPath("$.content[0].name", is("exampleItemShoes")))
        .andExpect(jsonPath("$.content[1].name", is("exampleItemWatch")));
  }
}
