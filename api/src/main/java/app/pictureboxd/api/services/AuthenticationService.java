package app.pictureboxd.api.services;

import app.pictureboxd.api.dto.AuthenticationResponseDto;
import app.pictureboxd.api.dto.SigninRequestDto;
import app.pictureboxd.api.dto.SignupRequestDto;

public interface AuthenticationService {
  AuthenticationResponseDto signup(SignupRequestDto request);

  AuthenticationResponseDto signin(SigninRequestDto request);

  // void refreshToken(HttpServletRequest request, HttpServletResponse response)
  // throws IOException;
}
