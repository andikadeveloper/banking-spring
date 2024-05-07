package com.andikadeveloper.bankingspring.user.controller;

import com.andikadeveloper.bankingspring.user.model.dto.response.UserResponse;
import com.andikadeveloper.bankingspring.user.service.contract.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getAuthenticatedUser() {
        UserResponse response = userService.getAuthenticatedUser();

        return ResponseEntity.ok(response);
    }
}
