package com.apple.shop.member;

import com.apple.shop.member.Member;
import org.springframework.data.jpa.repository.JpaReository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByUsername(String username);
  
}
