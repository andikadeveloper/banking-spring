package com.andikadeveloper.bankingspring.user.service.impl;

import com.andikadeveloper.bankingspring.user.model.dto.request.LoginRequest;
import com.andikadeveloper.bankingspring.user.model.dto.request.RegisterRequest;
import com.andikadeveloper.bankingspring.user.model.dto.response.LoginResponse;
import com.andikadeveloper.bankingspring.user.model.dto.response.RegisterResponse;
import com.andikadeveloper.bankingspring.user.model.entity.RoleEntity;
import com.andikadeveloper.bankingspring.user.model.entity.RoleEnum;
import com.andikadeveloper.bankingspring.user.model.entity.UserEntity;
import com.andikadeveloper.bankingspring.user.repository.RoleRepository;
import com.andikadeveloper.bankingspring.user.repository.UserRepository;
import com.andikadeveloper.bankingspring.user.service.contract.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        log.info("Register request: {}", request);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Optional<RoleEntity> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            log.info("Role not found");
            throw new RuntimeException("Role not found");
        }

        RoleEntity role = optionalRole.get();

        UserEntity user = new UserEntity();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(role);
        userRepository.save(user);

        log.info("User registered: {}", user);

        RegisterResponse response = new RegisterResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        log.info("Login request: {}", request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        log.info("User logged in: {}", request.getEmail());

        UserEntity user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        log.info("User found: {}", user.getId());

        String jwtToken = jwtService.generateToken(user);

        LoginResponse response = new LoginResponse();
        response.setToken(jwtToken);
        response.setExpiredIn(jwtService.getExpirationTime());

        log.info("Token generated: {}", jwtToken);

        return response;
    }
}
