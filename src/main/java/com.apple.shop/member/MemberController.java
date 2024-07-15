package com.apple.shop.member;

import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MemberController {
  
  private final MemberRepository memberRepository;
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  @GetMapping("/register")
  String register() {
    return "register.html";
  }

  @PostMapping("/member")
  public String addMember(
          String username,
          String password,
          String displayName
  ) {
    Member member = new Member();
    member.setUsername(username);
    var hash = new BCryptPasswordEncoder().encode(password);
    member.setPassword(hash);
    member.setDisplayName(displayName);
    memberRepository.save(member);

    return "redirect:/list";
  }

  @GetMapping("/login")
  public String login() {
    return "login.html";
  }
  
  @GetMapping("/my-page")
  public String myPage(Authentication auth) {
    // System.out.println(auth);
    // System.out.println(auth.getName());
    // System.out.println(auth.isAuthenticated());
    // System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저")));
    Customer result = (Customer) auth.getPrincipal();
    System.out.println(result.displayName);
    return "mypage.html";
  }

  
  @GetMapping("/user/1")
  @ResponseBody
  public MemberDto getUser() {
    var a = memberRepository.findById(1L);
    var result = a.get();
    var data = new MemberDto(result.getUsername(), result.getDisplayName());
    return data;
  }

  @PostMapping("/login/jwt")
  @ResponseBody
  public String loginJWT(@RequestBody Map<String, String> data) {

    var authToken = new UsernamePasswordAuthenticationToken(
      data.get("username"), data.get("password")
    );
   var auth = authenticationManagerBuilder.getObject().authenticate(authToken);  
   SecurityContextHolder.getContext().setAuthentication(auth); 

   return "";
  }

}

class MemberDto {
  public String username;
  public String displayName;
  MemberDto(String a, String b) {
    this.username = a;
    this.displayName = b;
  }
}
