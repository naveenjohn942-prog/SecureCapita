package io.getarrays.securecapita.query;

public class RoleQuery {
    public static final String SELECT_ROLE_BY_NAME_QUERY = "SELECT * FROM Roles WHERE name = :roleName";

    public static final String INSERT_ROLE_TO_USER_QUERY = "INSERT INTO UserRoles (user_id, role_id) values (:userId, :roleId)";

    public static final String FETCH_ROLE_ID_FROM_USER_ROLES_BY_USER_ID_QUERY = "SELECT r.id, r.name, r.permissions FROM Roles r JOIN UserRoles ur ON ur.role_id = r.id JOIN Users u ON u.id = ur.user_id WHERE u.id = :userId";
}
