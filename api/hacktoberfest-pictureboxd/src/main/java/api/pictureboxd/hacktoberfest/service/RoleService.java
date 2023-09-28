package api.pictureboxd.hacktoberfest.service;

import java.util.Optional;
import java.util.UUID;

import api.pictureboxd.hacktoberfest.domain.Role;

public interface RoleService {

  Role create(Role role);

  Optional<Role> getById(UUID id);

  Optional<Role> getByTitle(String title);
}
