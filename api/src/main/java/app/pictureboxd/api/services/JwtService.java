package app.pictureboxd.api.services;

import java.util.function.Function;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
  String extractUsername(String token);

  <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  String generateToken(Authentication authentication);

  String generateRefreshToken(Authentication authentication);

  boolean isTokenValid(String token, UserDetails userDetails);
}
