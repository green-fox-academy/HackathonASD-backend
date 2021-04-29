package com.greenfox.hackathon.repository;

import com.greenfox.hackathon.model.Item;
import com.greenfox.hackathon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
  Optional<Item> findItemByName(String name);
}
