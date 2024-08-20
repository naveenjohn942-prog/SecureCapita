package io.getarrays.securecapita.repository.impl;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.enumeration.VerificationType;
import io.getarrays.securecapita.exception.ApiException;
import io.getarrays.securecapita.form.UpdateForm;
import io.getarrays.securecapita.model.Role;
import io.getarrays.securecapita.model.User;
import io.getarrays.securecapita.model.UserPrincipal;
import io.getarrays.securecapita.repository.RoleRepository;
import io.getarrays.securecapita.repository.UserRepository;
import io.getarrays.securecapita.rowMapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;
import static java.util.Map.of;
import static io.getarrays.securecapita.enumeration.RoleType.ROLE_USER;
import static io.getarrays.securecapita.enumeration.VerificationType.ACCOUNT;
import static io.getarrays.securecapita.enumeration.VerificationType.PASSWORD;
import static io.getarrays.securecapita.query.UserQuery.*;
import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.time.DateFormatUtils.format;
import static org.apache.commons.lang3.time.DateUtils.addDays;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User>, UserDetailsService {

    private final NamedParameterJdbcTemplate jdbc;
    @Autowired
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
    @Override
    @Transactional
    public User create(User user) {
        if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0) {
            throw new ApiException("Email already in use");
        }

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, keyHolder);
            user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());

            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            jdbc.update(INSERT_ACCOUNT_VERIFICATION_URL_QUERY, Map.of("userId", user.getId(), "url", verificationUrl));

            // Placeholder for email service integration
            // emailService.sendVerificationUrl(user.getFirstName(), user.getEmail(), verificationUrl, ACCOUNT);

            user.setEnabled(false); ///////// for disabling the user
            user.setNonLocked(true);
            log.info("User created successfully with email: {}", user.getEmail());
            return user;
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new ApiException("An error occurred while creating the user.");
        }
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return List.of(); // To be implemented
    }

    @Override
    public User get(Long id) {
        try{
            return jdbc.queryForObject(GET_USER_BY_ID, of("id", id), new UserRowMapper());
        }
        catch(EmptyResultDataAccessException e ){
            log.error(e.getMessage());
            throw new ApiException(e.getMessage());
        }
        catch(Exception e ){
            log.error(e.getMessage());
            throw new ApiException("An error occurred");
        }
    }

    @Override
    public User update(User data) {
        return null; // To be implemented
    }

    @Override
    public Boolean delete(Long id) {
        return null; // To be implemented
    }

    private Integer getEmailCount(String email) {
        try {
            return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
        } catch (Exception e) {
            log.error("Error getting email count: {}", e.getMessage());
            throw new ApiException("An error occurred while checking email count.");
        }
    }

    private SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
    }

    private String getVerificationUrl(String key, String type) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "/" + key).toUriString();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        if (user == null) {
            log.error("User not found with email: {}", email);
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found with email: {}", email);
            return new UserPrincipal(user, roleRepository.getRoleByUserId(user.getId()));
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return jdbc.queryForObject(SELECT_USER_BY_EMAIL_QUERY, Map.of("email", email), new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            log.error("User not found with email: {}", email);
            throw new ApiException("User not found by email " + email);
        } catch (Exception e) {
            log.error("Error fetching user by email: {}", e.getMessage());
            throw new ApiException("An error occurred while fetching the user.");
        }
    }

    @Override
    public void sendVerificationCode(UserDTO user) {
        String expirationDate = format(addDays(new Date(),1),DATE_FORMAT);
        String verificationCode = randomAlphabetic(8).toUpperCase();
        try {
            jdbc.update(DELETE_VERIFICATION_CODE_BY_USER_ID, Map.of("id", user.getId()));
            jdbc.update(INSERT_VERIFICATION_CODE_QUERY, Map.of("userId", user.getId(),"code", verificationCode,"expDate", expirationDate));
//            sendSMS(user.getPhone(),"From: SecureCapita \nVerification Code\n+verificationCode");
            log.info("Verification code sent:{}", verificationCode);
        } catch (Exception e) {
            throw new ApiException("An error occurred.");
        }
    }

    @Override
    public User verifyCode(String email, String code) {
        if (isVerificationCodeExpired(code)) {
            throw new ApiException("The code has expired.Please try again.");
        }
        try {
            User userByCode = jdbc.queryForObject(SELECT_USER_BY_USER_CODE_QUERY, Map.of("code", code), new UserRowMapper());
            User userByEmail = jdbc.queryForObject(SELECT_USER_BY_EMAIL_QUERY, Map.of("email", email), new UserRowMapper());
            if(userByCode.getEmail().equalsIgnoreCase(userByEmail.getEmail())) {
                jdbc.update(DELETE_VERIFICATION_CODE_BY_USER_ID_AND_CODE, Map.of("userId", userByCode.getId(), "code", code));
                return userByCode;
            }else {
                throw new ApiException("User not found with email: " + email);
            }
        }catch (EmptyResultDataAccessException e) {
            throw new ApiException("User not found with email: " + email);
        }catch (Exception e) {
            throw new ApiException("An error occurred.");
        }
    }

    @Override
    public void resetPassword(String email) {
        if(getEmailCount(email.trim().toLowerCase()) <= 0)
            throw new ApiException("There is no user with email: " + email);
        try {
                String expirationDate = format(addDays(new Date(),1),DATE_FORMAT);
                User user = getUserByEmail(email);
                String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(),PASSWORD.getType());
                jdbc.update(DELETE_PASSWORD_VERIFICATION_BY_USER_ID_QUERY, of("userId",user.getId()));
                jdbc.update(INSERT_PASSWORD_VERIFICATION_QUERY, of("userId", user.getId(), "url", verificationUrl, "expirationDate", expirationDate));
                log.info("Verification URL: {}", verificationUrl);
        }catch (Exception e) {
            throw new ApiException("An error occurred.");
        }
    }

    @Override
    public User verifyPasswordKey(String key) {
        if(isLinkExpired(key, PASSWORD)) throw new ApiException("This link has expired. Please reset your password again");
        try{
            User user =  jdbc.queryForObject(SELECT_USER_BY_PASSWORD_URL_QUERY, of("url", getVerificationUrl(key, PASSWORD.getType())), new UserRowMapper());
            return user;
        }
        catch (EmptyResultDataAccessException e){
            log.error(e.getMessage());
            throw new ApiException("This link has expired. Please reset your password again");
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new ApiException("An error occurred");
        }
    }

    @Override
    public void renewPassword(String key, String password, String confirmPassword) {
        if(!password.equals(confirmPassword)) throw new ApiException("Password don't match please try again");
        try{
            jdbc.update(UPDATE_USER_PASSWORD_BY_URL_QUERY, of("password",encoder.encode(password),"url",getVerificationUrl(key,PASSWORD.getType())));
            jdbc.update(DELETE_VERIFICATION_BY_URL_QUERY, of("url",getVerificationUrl(key,PASSWORD.getType())));
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new ApiException("An error occurred");
        }
    }

    @Override
    public User verifyAccountKey(String key) {
        try {
            User user = jdbc.queryForObject(SELECT_USER_BY_ACCOUNT_URL_QUERY, Map.of("url", getVerificationUrl(key, ACCOUNT.getType())), new UserRowMapper());
            jdbc.update(UPDATE_USER_ENABLED_QUERY,of("enabled", true, "id",user.getId()));
            return user;
        }catch (EmptyResultDataAccessException e) {
            throw new ApiException("This link is not valid. Please try again.");
        }catch (Exception e) {
            throw new ApiException("An error occurred.");
        }
    }

    @Override
    public User updateUserDetails(UpdateForm user) {
        try{
            jdbc.update(UPDATE_USER_DETAILS_QUERY, getUserDetailsSqlParameterSource(user));
            return get(user.getId());
        }
        catch (EmptyResultDataAccessException e){
            log.error(e.getMessage());
            throw new ApiException("No user found by user id: "+ user.getId());
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException("An error occurred.");
        }
    }

    @Override
    public void updatePassword(Long id, String newPassword, String currentPassword, String confirmPassword) {
        if(!newPassword.equals(confirmPassword)) {
            throw new ApiException("Password don't match please try again");
        }
        User user = get(id);
        if (encoder.matches(currentPassword, user.getPassword())) {
            try {
                jdbc.update(UPDATE_USER_PASSWORD_BY_ID_QUERY, of("userId", id, "password", encoder.encode(newPassword)));
            } catch (Exception e) {
                throw new ApiException("An error occurred");
            }
        }else {
            throw new ApiException("Password is incorrect please try again");
        }

    }

    private SqlParameterSource getUserDetailsSqlParameterSource(UpdateForm user) {
        return new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("phone", user.getPhone())
                .addValue("address", user.getAddress())
                .addValue("enabled", user.getEnabled())
                .addValue("email", user.getEmail())
                .addValue("title", user.getTitle())
                .addValue("bio", user.getBio());
    }

    private Boolean isLinkExpired(String key, VerificationType password) {
        try{
            return jdbc.queryForObject(SELECT_EXPIRATION_BY_URL, of("url", getVerificationUrl(key, password.getType())), Boolean.class);
        }
        catch (EmptyResultDataAccessException e){
            log.error(e.getMessage());
            throw new ApiException("This link has expired. Please reset your password again");
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new ApiException("An error occurred");
        }
    }

    private Boolean isVerificationCodeExpired(String code) {
        try {
            return jdbc.queryForObject(SELECT_CODE_EXPIRATION_QUERY, Map.of("code", code), Boolean.class);
        }catch (EmptyResultDataAccessException e) {
            throw new ApiException("This code is Invalid. Please try again.");
        }catch (Exception e) {
            throw new ApiException("An error occurred.");
        }
    }
}
