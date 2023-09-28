package api.pictureboxd.hacktoberfest.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.pictureboxd.hacktoberfest.domain.Role;
import api.pictureboxd.hacktoberfest.repository.RoleRepository;
import api.pictureboxd.hacktoberfest.service.RoleService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role create(Role role) {
    return roleRepository.save(role);
  }

  @Override
  public Optional<Role> getById(UUID id) {
    return roleRepository.findById(id);
  }

  @Override
  public Optional<Role> getByTitle(String title) {
    return roleRepository.findRoleByTitle(title);
  }
}
