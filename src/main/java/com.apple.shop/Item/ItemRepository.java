package com.apple.shop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageble;  
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

  Page<Item> findPageBy(Pageble page);
  List<Item> findAllByTitleContains(String title);

  @Query(value = "select * from item where match(title) against(?1)", nativeQuery = true)
  List<Item> rawQuery1(String text);

} 
