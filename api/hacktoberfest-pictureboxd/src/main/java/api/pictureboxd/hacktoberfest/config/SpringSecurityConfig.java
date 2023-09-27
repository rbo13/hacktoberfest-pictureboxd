package api.pictureboxd.hacktoberfest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((authorize) -> {
          // authorize.requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN",
          // "USER");
          // authorize.requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN");
          // authorize.requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN");

          // allow all GET http request to be public.
          authorize.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll();

          authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

    return httpSecurity.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails chardy = User
        .builder()
        .username("chardyy")
        .password(passwordEncoder().encode("password"))
        .roles("USER")
        .build();

    return new InMemoryUserDetailsManager(chardy);
  }
}
