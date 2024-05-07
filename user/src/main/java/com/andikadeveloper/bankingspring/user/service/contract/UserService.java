package com.andikadeveloper.bankingspring.user.service.contract;

import com.andikadeveloper.bankingspring.user.model.dto.response.UserResponse;

public interface UserService {
    UserResponse getAuthenticatedUser();
}
