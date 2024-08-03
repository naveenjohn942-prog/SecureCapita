package io.getarrays.securecapita.repository.impl;

import io.getarrays.securecapita.expcetion.ApiException;
import io.getarrays.securecapita.model.Role;
import io.getarrays.securecapita.model.User;
import io.getarrays.securecapita.repository.RoleRepository;
import io.getarrays.securecapita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

import static io.getarrays.securecapita.enumeration.RoleType.ROLE_USER;
import static io.getarrays.securecapita.enumeration.VerificationType.ACCOUNT;
import static io.getarrays.securecapita.query.UserQuery.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

    private final NamedParameterJdbcTemplate jdbc;
    @Autowired
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User create(User user) {
        //verify email
        if(getEmailCount(user.getEmail().trim().toLowerCase())>0)throw new ApiException("Email already in use");
        //saving
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY,parameters,keyHolder);
            user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
            //Adding Roles to user
            roleRepository.addRoleToUser(user.getId(),ROLE_USER.name());
            //send verification
            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(),ACCOUNT.getType());
            //save url in verification table
            jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY,Map.of("userId",user.getId(),"url",verificationUrl));
            //send email for verification
//            emailService.sendVerificationUrl(user.getFirstName(),user.getEmail(),verificationUrl,ACCOUNT);
            user.setEnabled(false);
            user.setNonLocked(true);
            return user;
        }catch (Exception e){
            throw new ApiException("An error occured.");
        }

    }




    @Override
    public Collection list(int page, int pageSize) {
        return List.of();
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private Integer getEmailCount(String email) {
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email",email),Integer.class);
    }
    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName",user.getFirstName())
                .addValue("lastName",user.getLastName())
                .addValue("email",user.getEmail())
                .addValue("password",encoder.encode(user.getPassword()));
    }
    private String getVerificationUrl(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/"+type+"/"+key).toUriString();
    }
}
