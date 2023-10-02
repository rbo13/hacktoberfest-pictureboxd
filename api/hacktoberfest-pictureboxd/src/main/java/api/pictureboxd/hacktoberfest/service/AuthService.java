package api.pictureboxd.hacktoberfest.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import api.pictureboxd.hacktoberfest.dto.RegisterDto;

public interface AuthService extends UserDetailsService {

  String register(RegisterDto request);
}
