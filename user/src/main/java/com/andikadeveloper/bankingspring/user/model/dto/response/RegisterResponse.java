package com.andikadeveloper.bankingspring.user.model.dto.response;

import com.andikadeveloper.bankingspring.user.model.entity.RoleEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class RegisterResponse {
    private Long id;
    private String fullName;
    private String email;
    private RoleEntity role;
}
