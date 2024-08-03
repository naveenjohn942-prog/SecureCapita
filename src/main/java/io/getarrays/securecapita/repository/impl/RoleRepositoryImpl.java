package io.getarrays.securecapita.repository.impl;

import io.getarrays.securecapita.expcetion.ApiException;
import io.getarrays.securecapita.model.Role;
import io.getarrays.securecapita.repository.RoleRepository;
import io.getarrays.securecapita.rowMapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.getarrays.securecapita.enumeration.RoleType.ROLE_USER;
import static io.getarrays.securecapita.query.RoleQuery.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImpl implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbc;
    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection<Role> list(int page, int pageSize) {
        return List.of();
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("Adding role to user " + userId + " with role " + roleName);
        try{
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY,Map.of("name",roleName), new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY,Map.of("userId",userId,"roleId", Objects.requireNonNull(role).getId()));

        }catch (EmptyResultDataAccessException e){
            throw new ApiException("NO role found by name: "+ROLE_USER.name());
        }catch (Exception e){
            throw new ApiException("An error occured.");
        }
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public Role getRoleByUserEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

    }
}
