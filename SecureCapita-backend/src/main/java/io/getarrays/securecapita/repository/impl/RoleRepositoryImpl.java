package io.getarrays.securecapita.repository.impl;

import io.getarrays.securecapita.exception.ApiException;
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
    public Collection<Role> list() {
        log.info("Fetching all the roles" );
        try{
            return jdbc.query(SELECT_ROLES_QUERY,  new RoleRowMapper());
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new ApiException("an error occurred !!");
        }
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
        log.info("Fetching role {} to user id : {} ",roleName, userId );
        try{
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("roleName", roleName), new RoleRowMapper());
            jdbc.update(INSERT_ROLE_TO_USER_QUERY, Map.of("userId", userId, "roleId", role.getId()));
        }
        catch(EmptyResultDataAccessException e){
            log.error(e.getMessage());
            throw new ApiException("No role found by name" + ROLE_USER.name());
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new ApiException("an error occurred !!");
        }
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        try {
            Role role = jdbc.queryForObject(FETCH_ROLE_ID_FROM_USER_ROLES_BY_USER_ID_QUERY, Map.of("userId", userId), new RoleRowMapper());
            if (role == null) {
                throw new ApiException("No role found for user id: " + userId);
            }
            log.info("Role found for user id {}: {}", userId, role);
            return role;
        } catch (EmptyResultDataAccessException e) {
            log.error("No role found for user id {}: {}", userId, e.getMessage());
            throw new ApiException("No role found for user id: " + userId);
        } catch (Exception e) {
            log.error("An error occurred while fetching role for user id {}: {}", userId, e.getMessage(), e);
            throw new ApiException("Something went wrong.");
        }
    }

    @Override
    public Role getRoleByUserEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {
        log.info("Updating role {} to user id : {} ",roleName, userId );
        try{
            Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, Map.of("name", roleName), new RoleRowMapper());
            jdbc.update(UPDATE_USER_ROLE_QUERY, Map.of("roleId", role.getId(), "userId", userId));
        }
        catch(EmptyResultDataAccessException e){
            log.error(e.getMessage());
            throw new ApiException("No role found by name" + roleName);
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new ApiException("an error occurred !!");
        }
    }
}
