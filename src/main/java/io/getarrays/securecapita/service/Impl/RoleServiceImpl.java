package io.getarrays.securecapita.service.Impl;

import io.getarrays.securecapita.model.Role;
import io.getarrays.securecapita.repository.RoleRepository;
import io.getarrays.securecapita.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository<Role> roleRepository;
    @Override
    public Role getRoleByUserId(Long id) {
        return roleRepository.getRoleByUserId(id);
    }
}
