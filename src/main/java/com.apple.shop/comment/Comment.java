package com.apple.shop.comment;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ToString
  
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  public String username;
  public String content;
  public Long parentId;
}
