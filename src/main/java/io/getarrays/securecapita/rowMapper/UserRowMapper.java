package io.getarrays.securecapita.rowMapper;

import io.getarrays.securecapita.model.Role;
import io.getarrays.securecapita.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp createdOnTimestamp = rs.getTimestamp("created_at");

        return User.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .phone(rs.getString("phone"))
                .address(rs.getString("address"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .enabled(rs.getBoolean("enabled"))
                .title(rs.getString("title"))
                .bio(rs.getString("bio"))
                .imageUrl(rs.getString("image_url"))
                .nonLocked(rs.getBoolean("non_locked"))
                .mfaEnabled(rs.getBoolean("mfa_enabled"))
                .createdOn(createdOnTimestamp != null ? createdOnTimestamp.toLocalDateTime() : null)
                .build();
    }
}