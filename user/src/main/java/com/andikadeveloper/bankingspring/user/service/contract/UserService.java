package com.andikadeveloper.bankingspring.user.service.contract;

import com.andikadeveloper.bankingspring.user.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getAuthenticatedUser();
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Integer id);
}
