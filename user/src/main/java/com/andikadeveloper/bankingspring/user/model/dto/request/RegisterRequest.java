package com.andikadeveloper.bankingspring.user.model.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = SnakeCaseStrategy.class)
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
}
