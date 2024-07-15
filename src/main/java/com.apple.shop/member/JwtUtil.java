package com.apple.shop.member;

@Component
public class JwtUtil {

  static final SecretKey key =
          Keys.hmacShaKeyFor(Decoders.BASE64.decode(
                  "jwtpassword123jwtpassword123jwtpassword123jwtpassword123jwtpassword"
          ));

  // JWT 만들어주는 함수
  public static String createToken(Authentication auth) {
    var user = (Customer) auth.getPrincipal();
    var authorities = auth.getAuthorities().stream().map(a -> a.getAuthority())
      .collect(Collectors.joining(","));
    String jwt = Jwts.builder()
            .claim("username", user.getUsername())
            .claim("displayName", user.displayName)
            .claim("authorities", authorities)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + 10000)) //유효기간 10초
            .signWith(key)
            .compact();
    return jwt;
  }

  // JWT 까주는 함수
  public static Claims extractToken(String token) {
    Claims claims = Jwts.parser().verifyWith(key).build()
            .parseSignedClaims(token).getPayload();
    return claims;
  }

} 
