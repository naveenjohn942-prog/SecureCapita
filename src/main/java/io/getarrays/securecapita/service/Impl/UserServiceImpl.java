package io.getarrays.securecapita.service.Impl;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.dtoMapper.UserDTOMapper;
import io.getarrays.securecapita.model.User;
import io.getarrays.securecapita.repository.UserRepository;
import io.getarrays.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;
    private final UserDTOMapper userDTOMapper;

    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}
