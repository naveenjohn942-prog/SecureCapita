package io.getarrays.securecapita.service;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.form.UpdateForm;
import io.getarrays.securecapita.model.User;

public interface UserService {
    UserDTO createUser(User user);

    UserDTO getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);

    UserDTO verifyCode(String email, String code);

    void resetPassword(String email);

    UserDTO verifyPasswordKey(String key);

    void renewPassword(String key, String password, String confirmPassword);

    UserDTO verifyAccountKey(String key);

    UserDTO updateUserDetails(UpdateForm user);

    UserDTO getUserById(long userId);

    void updatePassword(Long id, String newPassword, String currentPassword, String confirmPassword);

    void updateUserRole(Long userId, String roleName);
}
