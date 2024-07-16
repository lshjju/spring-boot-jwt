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

    // Cookie[] cookies = request.getCookies();
    // System.out.println(cookies[0].getNam());
    // System.out.println("필터실행됨");
    // 요청들어올때마다 실행할코드~~
    filterChain.doFilter(request, response);
  }

}
