package com.andikadeveloper.bankingspring.user.service.contract;

import com.andikadeveloper.bankingspring.user.model.dto.request.LoginRequest;
import com.andikadeveloper.bankingspring.user.model.dto.request.RegisterRequest;
import com.andikadeveloper.bankingspring.user.model.dto.response.LoginResponse;
import com.andikadeveloper.bankingspring.user.model.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
