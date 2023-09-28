package api.pictureboxd.hacktoberfest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class SpringSecurityConfig {

  private UserDetailsService userDetailsService;

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new Argon2PasswordEncoder(16, 32, 1, 60000, 10);
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((authorize) -> {
          // authorize.requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyRole("ADMIN",
          // "USER");
          // authorize.requestMatchers(HttpMethod.PUT, "/api/v1/**").hasRole("ADMIN");
          // authorize.requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole("ADMIN");

          // allow signup and signin to be public
          authorize.requestMatchers(HttpMethod.POST, "/signup", "/signin").permitAll();

          // allow all GET http request to be public.
          authorize.requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll();

          authorize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

    return httpSecurity.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
}
