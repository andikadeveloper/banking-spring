package com.andikadeveloper.bankingspring.user.bootstrap;

import com.andikadeveloper.bankingspring.user.model.entity.RoleEntity;
import com.andikadeveloper.bankingspring.user.model.entity.RoleEnum;
import com.andikadeveloper.bankingspring.user.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadRoles();
    }

    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.USER, "Default user role",
                RoleEnum.ADMIN, "Administrator role"
        );

        Arrays.stream(roleNames).forEach(roleName -> {
            Optional<RoleEntity> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setName(roleName);
                roleEntity.setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleEntity);
            });
        });
    }
}
