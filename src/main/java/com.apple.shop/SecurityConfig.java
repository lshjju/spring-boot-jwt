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
    http.csrf((csrf) -> csrf.disable());
    
    http.sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );
    
http.addFilterBefore(new JwtFilter()), ExceptionTranslationFilter.class);    http.authorizeHttpRequests((authorize) ->
        authorize.requestMatchers("/**").permitAll()
    );

    // http.formLogin((formLogin) 
    //                -> formLogin.loginPage("/login")
    //                 .defaultSuccessUrl("/")
    // ); 

    
    http.logout(logout -> logout.logoutUrl("/logout"))
    
    return http.build();
  }

}
