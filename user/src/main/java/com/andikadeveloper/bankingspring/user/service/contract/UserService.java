package com.andikadeveloper.bankingspring.user.service.contract;

import com.andikadeveloper.bankingspring.user.model.dto.request.UpdateUserRequest;
import com.andikadeveloper.bankingspring.user.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    void deleteUserById(Long id);
    UserResponse updateUserById(Long id, UpdateUserRequest request);
}
