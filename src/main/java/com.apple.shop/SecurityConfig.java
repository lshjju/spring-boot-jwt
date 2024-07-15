package com.apple.shop;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  } 
  
// csrf 켜기
  // @Bean
  // public CsrfTokenRepository csrfTokenRepository() {
  //   HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
  //   repository.setHeaderName("X-XSRF-TOKEN");
  //   return repository;
  // }  
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// csrf 켜기
    //     http.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository())
//        .ignoringRequestMatchers("/login")
// )
    
    http.csrf((csrf) -> csrf.disable());
    http.authorizeHttpRequests((authorize) ->
        authorize.requestMatchers("/**").permitAll()
    );

    http.formLogin((formLogin) 
                   -> formLogin.loginPage("/login")
                    .defaultSuccessUrl("/")
                    // .failureUrl("/fail")
    ); 
    http.logout(logout -> logout.logoutUrl("/logout"))
    
    return http.build();
  }

}
