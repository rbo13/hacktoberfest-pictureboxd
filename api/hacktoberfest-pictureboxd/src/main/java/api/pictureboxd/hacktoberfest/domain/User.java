package api.pictureboxd.hacktoberfest.domain;

import java.util.UUID;

import api.pictureboxd.hacktoberfest.domain.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private UUID id;

  @Column(name = "email_address", nullable = false, unique = true)
  private String emailAddress;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "is_verified")
  @Builder.Default
  private Boolean isVerified = true;

  @Enumerated(EnumType.STRING)
  private Role role;
}
