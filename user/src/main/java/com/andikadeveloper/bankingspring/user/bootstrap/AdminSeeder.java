package com.andikadeveloper.bankingspring.user.bootstrap;

import com.andikadeveloper.bankingspring.user.model.dto.request.RegisterRequest;
import com.andikadeveloper.bankingspring.user.model.entity.RoleEntity;
import com.andikadeveloper.bankingspring.user.model.entity.RoleEnum;
import com.andikadeveloper.bankingspring.user.model.entity.UserEntity;
import com.andikadeveloper.bankingspring.user.repository.RoleRepository;
import com.andikadeveloper.bankingspring.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createAdmin();
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }

    private void createAdmin() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Admin");
        request.setEmail("admin@admin.com");
        request.setPassword("123456");

        Optional<RoleEntity> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        Optional<UserEntity> optionalUser = userRepository.findByEmail(request.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new UserEntity();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}
