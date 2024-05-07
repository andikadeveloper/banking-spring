package com.andikadeveloper.bankingspring.user.controller;

import com.andikadeveloper.bankingspring.user.model.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {

    public UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        return (UserEntity) authentication.getPrincipal();
    }
}
