package app.pictureboxd.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

  @JsonProperty("email_address")
  private String emailAddress;

  private String password;

  // Possible values: USER, SUPER_USER, ADMIN
  private String role;
}
