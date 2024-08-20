package io.getarrays.securecapita.repository;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.form.UpdateForm;
import io.getarrays.securecapita.model.User;

import java.util.Collection;

public interface UserRepository <T extends User>{
    T create(T data);
    Collection<T> list(int page,int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);
    User getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);

    User verifyCode(String email, String code);

    void resetPassword(String email);

    T verifyPasswordKey(String key);

    void renewPassword(String key, String password, String confirmPassword);

    T verifyAccountKey(String key);

    T updateUserDetails(UpdateForm user);

    void updatePassword(Long id, String newPassword, String currentPassword, String confirmPassword);
}
