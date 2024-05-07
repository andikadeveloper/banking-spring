package com.andikadeveloper.bankingspring.user.service.impl;

import com.andikadeveloper.bankingspring.user.model.dto.request.UpdateUserRequest;
import com.andikadeveloper.bankingspring.user.model.dto.response.UserResponse;
import com.andikadeveloper.bankingspring.user.model.entity.UserEntity;
import com.andikadeveloper.bankingspring.user.repository.UserRepository;
import com.andikadeveloper.bankingspring.user.service.contract.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        log.info("Get all users");

        List<UserResponse> users = userRepository
                .findAll()
                .stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setFullName(user.getFullName());
                    response.setEmail(user.getEmail());

                    return response;
                })
                .toList();

        log.info("Users Size: {}", users.size());

        return users;
    }

    @Override
    public UserResponse getUserById(Long id) {
        log.info("Get user by id: {}", id);

        UserEntity user = getUserByIdOrThrow(id);

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());

        log.info("User found: {}", response.getEmail());

        return response;
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Delete user by id: {}", id);

        getUserByIdOrThrow(id);

        userRepository.deleteById(id);

        log.info("User deleted");
    }

    @Override
    public UserResponse updateUserById(Long id, UpdateUserRequest request) {
        log.info("Update user by id: {}", id);

        UserEntity user = getUserByIdOrThrow(id);

        user.setFullName(request.getFullName());

        userRepository.save(user);

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());

        log.info("User updated: {}", response.getEmail());

        return response;
    }

    private UserEntity getUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found");
                    return new RuntimeException("User not found");
                });
    }
}
