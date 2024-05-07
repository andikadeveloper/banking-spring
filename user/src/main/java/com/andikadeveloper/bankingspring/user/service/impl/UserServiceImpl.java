package com.andikadeveloper.bankingspring.user.service.impl;

import com.andikadeveloper.bankingspring.user.model.dto.response.UserResponse;
import com.andikadeveloper.bankingspring.user.model.entity.UserEntity;
import com.andikadeveloper.bankingspring.user.repository.UserRepository;
import com.andikadeveloper.bankingspring.user.service.contract.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse getAuthenticatedUser() {
        log.info("Get authenticated user");

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        UserResponse response = new UserResponse();
        response.setId(currentUser.getId());
        response.setFullName(currentUser.getFullName());
        response.setEmail(currentUser.getEmail());

        log.info("User: {}", response);

        return response;
    }

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
    public UserResponse getUserById(Integer id) {
        log.info("Get user by id: {}", id);

        UserEntity user = userRepository.findById(id).orElse(null);

        if (user == null) {
            log.error("User not found");
            throw new RuntimeException("User not found");
        }

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());

        log.info("User found: {}", response.getEmail());

        return response;
    }
}
