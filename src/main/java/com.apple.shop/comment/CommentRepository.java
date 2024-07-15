package com.apple.shop.comment;

import org.springframework.data.jpa.repository.JpaReository;

import java.util.List;
// import com.apple.shop.member.Member;
// import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByParentId(Long parentId);
  
}
