package com.arcticnode.crm.Dto;

import com.arcticnode.crm.Entities.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String passwd;
    private String adminname;
    private UserType adminRole;
}