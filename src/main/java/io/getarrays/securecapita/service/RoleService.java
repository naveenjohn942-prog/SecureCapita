package io.getarrays.securecapita.service;

import io.getarrays.securecapita.model.Role;

import java.util.Collection;

public interface RoleService {
    Role getRoleByUserId(Long id);
    Collection<Role> getRoles();
}
