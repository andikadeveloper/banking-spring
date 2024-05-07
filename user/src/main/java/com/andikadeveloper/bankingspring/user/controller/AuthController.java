package com.andikadeveloper.bankingspring.user.controller;

import com.andikadeveloper.bankingspring.user.model.dto.request.RegisterRequest;
import com.andikadeveloper.bankingspring.user.model.dto.response.RegisterResponse;
import com.andikadeveloper.bankingspring.user.service.contract.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse user = userService.register(request);

        return ResponseEntity.ok(user);
    }
}
