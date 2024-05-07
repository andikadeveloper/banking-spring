package com.andikadeveloper.bankingspring.user.service.impl;

import com.andikadeveloper.bankingspring.user.model.dto.response.UserResponse;
import com.andikadeveloper.bankingspring.user.model.entity.UserEntity;
import com.andikadeveloper.bankingspring.user.service.contract.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

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
}
