package io.getarrays.securecapita.service;

import io.getarrays.securecapita.model.Role;

public interface RoleService {
    Role getRoleByUserId(Long id);
}
