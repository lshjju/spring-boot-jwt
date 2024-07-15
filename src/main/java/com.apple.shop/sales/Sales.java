package com.apple.shop.sales;

import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
public class Sales {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String ItemName;
  private Integer price;
  private Integer count;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "member_id", 
    foreignkey = @ForeignKey(ConstrainMode.NO_CONSTRAINT)
  )
  private Member member;
  
  @CreationTimestamp
  private LocalDateTime created;
}
