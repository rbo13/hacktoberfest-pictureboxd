package app.pictureboxd.api.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

  ADMIN_READ("admin:read");

  @Getter
  private final String permission;
}
