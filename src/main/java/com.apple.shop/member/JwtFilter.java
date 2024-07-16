package com.apple.shop.member;

import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, 
      HttpServletResponse response, 
      FilterChain filterChain
  ) throws ServletException, IOException {

    Cookie[] cookies = request.getCookies();
    if (cookies == null){
      filterChain.doFilter(request, response);
      return;
    }

    var jwtCookie = "";
    for (int i = 0; i < cookies.length; i++){
       if (cookies[i].getName().equals("jwt")){
         jwtCookie = cookies[i].getValue();
     }
  }

    Claims claim;
    try {
      claim = JwtUtil.extractToken(jwtCookie);
    } catch (Exception e){
      filterChain.doFilter(request, response);
      return;
    }

    var arr = claim.get("authorities").toString().split(",");
    var authorities = Arrays.stream(arr).map(a -> new SimpleGrantedAuthority(a)).toList();
    

    var customUser = new CustomUser(claim.get("username").toString(), "none", authorities );
    customUser.displayName = claim.get("displayName").toString();

    var authToken = new UsernamePasswordAuthenticationToken(
      customUser, ""
    );
    authToken.setDetails(new webAuthenticationDetailsSource()
                        .buildDetails(request)
                        );
    SecurityContextHolder.getContext().setAuthentication(authToken);
    
      
    //System.out.println(jwtCookie);
    // Cookie[] cookies = request.getCookies();
    // System.out.println(cookies[0].getName());
    // System.out.println("필터실행됨");
    // 요청들어올때마다 실행할코드~~
    filterChain.doFilter(request, response);
  }

}
