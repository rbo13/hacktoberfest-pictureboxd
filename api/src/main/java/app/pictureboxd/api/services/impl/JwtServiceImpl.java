package app.pictureboxd.api.services.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.pictureboxd.api.domain.User;
import app.pictureboxd.api.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;

  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  @Override
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  @Override
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  @Override
  public String generateToken(Authentication authentication) {
    return generateToken(new HashMap<>(), authentication);
  }

  @Override
  public String generateRefreshToken(Authentication authentication) {
    return buildToken(new HashMap<>(), authentication, refreshExpiration);
  }

  @Override
  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private String generateToken(
      Map<String, Object> extraClaims,
      Authentication authentication) {
    return buildToken(extraClaims, authentication, jwtExpiration);
  }

  private String buildToken(
      Map<String, Object> extraClaims,
      Authentication authentication,
      long expiration) {

    User user = getUser(authentication);

    if (user.equals(null)) {
      return "";
    }

    extraClaims.put("user_id", user.getId());
    extraClaims.put("role", user.getRole());

    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(authentication.getName())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private User getUser(Authentication authentication) {
    if (authentication.getPrincipal() instanceof User) {
      User user = (User) authentication.getPrincipal();
      return user;
    }

    throw new IllegalArgumentException("User not found");
  }
}
