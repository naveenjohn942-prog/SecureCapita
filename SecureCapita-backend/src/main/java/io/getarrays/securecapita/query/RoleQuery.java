package io.getarrays.securecapita.query;

/**
 * @author Junior RT
 * @version 1.0
 * @license Get Arrays, LLC (https://getarrays.io)
 * @since 8/28/2022
 */
public class RoleQuery {
    public static final String SELECT_ROLE_BY_NAME_QUERY = "SELECT * FROM roles WHERE name = :name";

    public static final String INSERT_ROLE_TO_USER_QUERY = "INSERT INTO userroles (user_id, role_id) values (:userId, :roleId)";

    public static final String FETCH_ROLE_ID_FROM_USER_ROLES_BY_USER_ID_QUERY = "SELECT r.id, r.name, r.permissions FROM roles r JOIN userroles ur ON ur.role_id = r.id JOIN users u ON u.id = ur.user_id WHERE u.id = :userId";

    public static final String SELECT_ROLES_QUERY = "SELECT * from roles order By id";

    public static final String UPDATE_USER_ROLE_QUERY = "UPDATE userroles SET role_id = :roleId WHERE user_id = :userId";
}