package io.getarrays.securecapita.service;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.model.User;

public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);

    User getUser(String email);

    UserDTO verifyCode(String email, String code);
}
